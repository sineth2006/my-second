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
public class StudentManagementController implements Initializable {

   
    
    @FXML
    private ComboBox<?> course_id_combobox;

    @FXML
    private TextField email;

    @FXML
    private TextField student_id;

    @FXML
    private Button student_management_back_button;

    @FXML
    private Button student_managment_add_button;

    @FXML
    private Button student_managment_delete_button;

    @FXML
    private Button student_managment_update_button;

    @FXML
    private TextField student_name;

    @FXML
    private TextField telephone_number;
      @FXML
    void student_managment_add_button_OnAction(ActionEvent event) {

    }

    @FXML
    void student_managment_delete_button_OnAction(ActionEvent event) {

    }

    @FXML
    void student_managment_update_button_OnAction(ActionEvent event) {

    }

    @FXML
   void student_management_back_button_OnAction(ActionEvent event)throws IOException, SQLException {


         
         Parent root=FXMLLoader.load(App.class.getResource("AdminDashboad.fxml"));
        Stage stage=new Stage();
        stage.setTitle("AdminDashboad");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)student_management_back_button.getScene().getWindow();
        currentStage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}