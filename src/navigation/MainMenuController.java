package navigation;

import com.util.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private ListView<String> menu;

    @FXML
    private AnchorPane aboutMeDesc;

    @FXML
    private AnchorPane calculatorDesc;

    @FXML
    private AnchorPane jungleJumpDesc;

    @FXML
    private Button logout;

    @FXML
    private Button select;

    @FXML
    void logoutButtonHandler(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("../loginPage/loginLayout.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.show();
    }

    @FXML
    void selectButtonHandler() throws IOException{
        String selected = menu.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        if (selected.equals("About me")){
            AlertBox.display("Note", "\"About Me\" is not an application.");
            return;
        }

        Stage window = new Stage();
        if(selected.equals("Calculator")) {
            window.setTitle(selected);
            Parent calculatorParent = FXMLLoader.load(getClass().getResource("../calculator/CalcLayout.fxml"));
            Scene calculatorScene = new Scene(calculatorParent);
            window.setScene(calculatorScene);
            window.show();
        }

        if(selected.equals("Jungle Jump")) {
            try {
                String command = "JungleJump/Jungle Jump.exe";
                Runtime run = Runtime.getRuntime();
                Process proc = run.exec(command);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @FXML
    void handleMenuSelect(){
        String selected = menu.getSelectionModel().getSelectedItem();

        if(!selected.equals("About me")) {
            aboutMeDesc.setVisible(false);
        }
        if(!selected.equals("Calculator")){
            calculatorDesc.setVisible(false);
        }
        if(!selected.equals("Jungle Jump")){
            jungleJumpDesc.setVisible(false);
        }

        switch(selected){
            case "About me":
                aboutMeDesc.setVisible(true);
                break;
            case "Calculator":
                calculatorDesc.setVisible(true);
                break;
            case "Jungle Jump":
                jungleJumpDesc.setVisible(true);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> projects = FXCollections.observableArrayList("About me", "Calculator", "Jungle Jump");
        menu.setItems(projects);
    }
}
