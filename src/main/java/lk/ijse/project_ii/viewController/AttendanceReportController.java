package lk.ijse.project_ii.viewController;

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
import javafx.stage.Stage;
import lk.ijse.project_ii.main.App;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class AttendanceReportController implements Initializable {

    @FXML
    private Button attendance_report_back_button;

    @FXML
    private Button attendance_report_clear_button;

    @FXML
    private Button attendance_report_generate_button;

    @FXML
    private ComboBox<?> studentId_combobox;

    @FXML
    private ComboBox<?> subjectid_combobox;

    
    @FXML
    void attendance_report_back_button_OnAction(ActionEvent event) throws IOException {
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
        Stage currentStage=(Stage)attendance_report_back_button.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void attendance_report_clear_button_OnAction(ActionEvent event) {

    }

    @FXML
    void attendance_report_generate_button_OnAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}