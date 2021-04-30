package loginPage;

import com.daoimpl.UserDaoImpl;
import com.util.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginLayoutController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text errorText;

    @FXML
    public void signInButtonHandle(ActionEvent event) throws IOException{
        if(usernameField.getText().trim().length() == 0 || passwordField.getText().trim().length() == 0) {
            errorText.setText("One or more fields is empty.");
        }else if(usernameField.getText().trim().equals("guest") && passwordField.getText().trim().equals("password")){
            Parent signInParent = FXMLLoader.load(getClass().getResource("../navigation/MainMenu.fxml"));
            Scene signInScene = new Scene(signInParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(signInScene);
            stage.show();
        } else {
            try {
                UserDaoImpl udi = new UserDaoImpl();
                udi.createUserTable();

                if (udi.checkLogin(usernameField.getText().trim(), passwordField.getText().trim())) {
                    Parent signInParent = FXMLLoader.load(getClass().getResource("../navigation/MainMenu.fxml"));
                    Scene signInScene = new Scene(signInParent);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(signInScene);
                    stage.show();
                } else {
                    errorText.setText("Wrong password or username");
                }
            } catch (Exception e) {
                AlertBox.display("Error", "Database not configured.  Use credentials: \n username: guest\n password: password");
            }
        }
    }

    public void signUpButtonHandle(ActionEvent event) throws IOException {
        Parent signInParent = FXMLLoader.load(getClass().getResource("../signupPage/SignupLayout.fxml"));
        Scene signInScene = new Scene(signInParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(signInScene);
        stage.show();
    }
}