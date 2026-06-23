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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class LectureDashboadController implements Initializable {

    @FXML
    private AnchorPane admin_dashboad;

    @FXML
    private Button attendance_marking_button;

    @FXML
    private Button attendance_reporting_button;

    @FXML
    private Button back_button;

    @FXML
    private Button class_sheduling_button;

    @FXML
    void lecture_attendance_marking_button_OnAction(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(App.class.getResource("AttendanceMarking.fxml"));
        Stage stage=new Stage();
        stage.setTitle("CouseManagement");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)attendance_marking_button.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void lecture_attendance_reporting_button_OnAction(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(App.class.getResource("AttendanceReport.fxml"));
        Stage stage=new Stage();
        stage.setTitle("CouseManagement");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)attendance_reporting_button.getScene().getWindow();
        currentStage.close();
    }

     @FXML
    void lecture_class_sheduling_button_OnAction(ActionEvent event) throws IOException {
         Parent root=FXMLLoader.load(App.class.getResource("ClassSheduling.fxml"));
        Stage stage=new Stage();
        stage.setTitle("CouseManagement");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)class_sheduling_button.getScene().getWindow();
        currentStage.close();
    }
    
    @FXML
    void lecture_back_button_OnAction(ActionEvent event)throws IOException, SQLException {
        Parent root=FXMLLoader.load(App.class.getResource("LogingUI.fxml"));
        Stage stage=new Stage();
        stage.setTitle("LogingUI");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)back_button.getScene().getWindow();
        currentStage.close();

    }
  
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}