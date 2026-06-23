package lk.ijse.project_ii.viewController;

import lk.ijse.project_ii.main.App;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class LectureManagementController implements Initializable {

  

    @FXML
    private TextField email;

    @FXML
    private TextField lecture_id;

    @FXML
    private Button lecture_management_back_button;

    @FXML
    private TextField lecture_name;

    @FXML
    private Button student_mamagment_add_button;

    @FXML
    private Button student_mamagment_delete_button;

    @FXML
    private Button student_mamagment_update_button;

    @FXML
    private ComboBox<?> subject_id;

    @FXML
    private TextField telephone_number;

    @FXML
    private ComboBox<?> user_id;
     @FXML
    void lecture_management_back_button_OnAction(ActionEvent event) throws IOException {

           
         Parent root=FXMLLoader.load(App.class.getResource("AdminDashboad.fxml"));
        Stage stage=new Stage();
        stage.setTitle("AdminDashboad");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)lecture_management_back_button.getScene().getWindow();
        currentStage.close();
    }

      @FXML
    void lecture_managment_add_button_OnAction(ActionEvent event) {

    }

    @FXML
    void lecture_managment_delete_button_OnAction(ActionEvent event) {

    }

    @FXML
    void lecture_managment_update_button_OnAction(ActionEvent event) {

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}