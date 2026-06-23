package lk.ijse.project_ii.viewController;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_ii.main.App;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class AdminDashboadController implements Initializable {

    
    @FXML
    private AnchorPane admin_dashboad;

    @FXML
    private AnchorPane admin_main;

    @FXML
    private Button attendance_marking_button;

    @FXML
    private Button attendance_reporting_button;

       @FXML
    private Button log_out_button;

    @FXML
    private Button class_sheduling_button;

    @FXML
    private Button couse_management_button;

    @FXML
    private Button lecture_management_button;
    
      @FXML
    private Button student_management_button;
    
      
       @FXML
    void couse_management_button_OnAction(ActionEvent event)throws IOException, SQLException {
        Parent root=FXMLLoader.load(App.class.getResource("CouseManagement.fxml"));
        Stage stage=new Stage();
        stage.setTitle("CouseManagement");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)couse_management_button.getScene().getWindow();
        currentStage.close();
    }
    
      @FXML
    void student_management_button_OnAction(ActionEvent event)throws IOException, SQLException {
         Parent root=FXMLLoader.load(App.class.getResource("StudentManagement.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Student Management");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)student_management_button.getScene().getWindow();
        currentStage.close();
    }
   
   
    
      @FXML
    void lecture_management_button_OnAction(ActionEvent event) throws IOException {
         Parent root=FXMLLoader.load(App.class.getResource("LectureManagement.fxml"));
        Stage stage=new Stage();
        stage.setTitle("LectureManagement");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)lecture_management_button.getScene().getWindow();
        currentStage.close();
        
    }
    
    
    @FXML
    void class_sheduling_button_OnAction(ActionEvent event) throws IOException {
           Parent root=FXMLLoader.load(App.class.getResource("ClassSheduling.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Class Sheduling");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)class_sheduling_button.getScene().getWindow();
        currentStage.close();
        
        
    }
    
     @FXML
    void attendance_marking_button_OnAction(ActionEvent event) throws IOException {
             Parent root=FXMLLoader.load(App.class.getResource("AttendanceMarking.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Attendance Marking");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)attendance_marking_button.getScene().getWindow();
        currentStage.close();
    }
    
    
    @FXML
    void attendance_reporting_button_OnAction(ActionEvent event) throws IOException {
        
             Parent root=FXMLLoader.load(App.class.getResource("AttendanceReport.fxml"));
        Stage stage=new Stage();
        stage.setTitle("Attendance Marking");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)attendance_reporting_button.getScene().getWindow();
        currentStage.close();

    }
     @FXML
    void log_out_button_OnAction(ActionEvent event)throws IOException, SQLException {
        
        
         Parent root=FXMLLoader.load(App.class.getResource("LogingUI.fxml"));
        Stage stage=new Stage();
        stage.setTitle("LogingUI");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)log_out_button.getScene().getWindow();
        currentStage.close();
    }

    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}