package lk.ijse.project_ii.viewController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.bo.AttendanceReportBo;
import lk.ijse.project_ii.bo.impl.AttendanceReportBoImpl;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.entity.AttendanceReportEntity;
import lk.ijse.project_ii.main.App;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class AttendanceReportController implements Initializable {

    private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   
    @FXML
    private Button attendance_report_back_button;

    @FXML
    private Button attendance_report_clear_button;

    @FXML
    private Button attendance_report_generate_button;

    @FXML
    private ComboBox<String> studentId_combobox;

    @FXML
    private ComboBox<String> subjectid_combobox;
    
     @FXML
    private TableView<AttendanceReportEntity> attendance_report_table;
    
     
    @FXML
    private DatePicker date_range1;

    @FXML
    private DatePicker date_range2;
    
    @FXML
    private TableColumn<AttendanceReportEntity, String> date_range_column;

    @FXML
    private TableColumn<AttendanceReportEntity, String> status_column;
    
    
    @FXML
    private TableColumn<AttendanceReportEntity,Integer> student_id_column;

    @FXML
    private TableColumn<AttendanceReportEntity, String> student_name_column;

    @FXML
    private TableColumn<AttendanceReportEntity, String> subject_column;
    
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
      private final AttendanceReportBo reportBO = new AttendanceReportBoImpl();
    @FXML
    void attendance_report_clear_button_OnAction(ActionEvent event) {

         studentId_combobox.getSelectionModel().clearSelection();
         subjectid_combobox.getSelectionModel().clearSelection();
        date_range1.setValue(null);
           date_range2.setValue(null);
        attendance_report_table.getItems().clear();
    }
    
    
    @FXML
    private void attendance_report_table_mouse_clicked(javafx.scene.input.MouseEvent event) {
    System.out.println("Row clicked");
}

    @FXML
    void attendance_report_generate_button_OnAction(ActionEvent event) {
        attendance_report_table.getItems().clear();
        
        String selectedStudentName = studentId_combobox.getValue();
        String selectedSubjectName = subjectid_combobox.getValue();
        LocalDate fromDate = date_range1.getValue();
        LocalDate toDate = date_range2.getValue();

        if (selectedStudentName == null || selectedSubjectName == null || fromDate == null || toDate == null) {
            System.out.println("Please select all fields.");
            return;
        }

        try {
            // Get DTO Data array from Business Object layer 
            var dtoList = reportBO.generateReport(selectedStudentName, selectedSubjectName, fromDate, toDate);
            
            // Map DTO payload items straight down into localized UI entities array list wrapper
            var entityList = FXCollections.<AttendanceReportEntity>observableArrayList();
            for (var dto : dtoList) {
                entityList.add(new AttendanceReportEntity(
                    dto.getStudentId(),
                    dto.getStudentName(),
                    dto.getSubjectName(),
                    dto.getAttendanceDate(),
                    dto.getStatus()
                ));
            }

            attendance_report_table.setItems(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  
    @FXML
    void load_combobox_table() {
          studentId_combobox.getItems().clear();
        subjectid_combobox.getItems().clear();
        try {
            studentId_combobox.getItems().addAll(reportBO.loadStudents());
            subjectid_combobox.getItems().addAll(reportBO.loadSubjects());
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       load_combobox_table();
       
        student_id_column.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        student_name_column.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        subject_column.setCellValueFactory(new PropertyValueFactory<>("subject"));
        date_range_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));

    }    
    
}