package signupPage;

import com.daoimpl.UserDaoImpl;
import com.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text errorText;

    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker birthField;

    @FXML
    void backButtonHandle(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("../loginPage/loginLayout.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.show();
    }

    @FXML
    void submitButtonHandle() {
        UserDaoImpl udi = new UserDaoImpl();
        udi.createUserTable();

        if(usernameField.getText().trim().length() == 0 || passwordField.getText().trim().length() == 0) {
            errorText.setText("One or more fields is empty.");
        }else{
            User user = new User(usernameField.getText().trim(), passwordField.getText().trim());
            errorText.setText(udi.insert(user));
        }
    }

}