package lk.ijse.project_ii.viewController;

import lk.ijse.project_ii.main.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class ClassShedulingController implements Initializable {

     @FXML
    private DatePicker class_date;

    @FXML
    private Button class_sheduling_add_button;

    @FXML
    private Button class_sheduling_back_button;

    @FXML
    private Button class_sheduling_delete_button;

    @FXML
    private Button class_sheduling_update_button;

    @FXML
    private ComboBox<?> class_time;

    @FXML
    private ComboBox<?> couse_id;

    @FXML
    private ComboBox<?> lecture_id;

    @FXML
    private TextField student_id;

    @FXML
    private ComboBox<?> subject_id;

    @FXML
    void class_sheduling_add_button_OnAction(ActionEvent event) {

    }
   
    
    @FXML
    void class_sheduling_back_button_OnAction(ActionEvent event) throws IOException {
        //creates the scheduling screen and explicitly passes the word "Admin" into your setUserole method:       
        String fxmlPath ;
        String title;
        
        if( "Lecturer".equals(App.currentUserRole)){
            fxmlPath="LectureDashboad.fxml";
            title="Lecture Dashboad";
       
         }else{
             fxmlPath="AdminDashboad.fxml";
            title="Admin Dashboad";
          }
        Parent root=FXMLLoader.load(App.class.getResource(fxmlPath));
        Stage stage=new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)class_sheduling_back_button.getScene().getWindow();
        currentStage.close();
        
    }

    @FXML
    void class_sheduling_delete_button_OnAction(ActionEvent event) {

    }

    @FXML
    void class_sheduling_update_button_OnAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}