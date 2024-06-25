
package bibliotheque_gestion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;



public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private Button Sign_in_btn;
    
    
    @FXML
    private Button Quit_btn;
    @FXML
    private PasswordField password;
    
    Login_service service = new Login_service();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //service.ajouterManager("jerbi","jerbiahmed@gmail.com","12345678");
        
    }    

    @FXML
    private void Login_clicked(MouseEvent event) throws IOException {
        int role = service.login(username.getText(), password.getText());
        switch (role) {
            case 0:
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Donneés non valides");
                    alert.setContentText("Verifier vos données ");
                    alert.showAndWait();
                    break;
                }
            
            case 2:
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Welcome Manager");
                    alert.setHeaderText("Success");
                    alert.setContentText("Welcome "+username.getText()+" to manage our library");
                    alert.showAndWait();
                    Parent root = FXMLLoader.load(getClass().getResource("Manager_Interface.fxml"));
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    Scene sc = new Scene(root);
                    stage.setScene(sc);
                    stage.show();
                    break;
                }
            
            default:
                break;
        }
        
    }

   
    @FXML
    private void quitter(MouseEvent event) {
        System.exit(0);
    }
    
}
