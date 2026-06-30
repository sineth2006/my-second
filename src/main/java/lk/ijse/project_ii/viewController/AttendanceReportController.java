package lk.ijse.project_ii.viewController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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

    @FXML
    void attendance_report_clear_button_OnAction(ActionEvent event) {

         studentId_combobox.getSelectionModel().clearSelection();
         subjectid_combobox.getSelectionModel().clearSelection();
        date_range1.setValue(null);
           date_range2.setValue(null);
        attendance_report_table.getItems().clear();
    }

    @FXML
    void attendance_report_generate_button_OnAction(ActionEvent event) {
           attendance_report_table.getItems().clear();
           
           
            String selectedStudentName = studentId_combobox.getValue();
            String selectedSubjectName = subjectid_combobox.getValue();
            
             int studentid= getStudentIdByName(selectedStudentName);
            int subjectid=getSubjectIdByName( selectedSubjectName);
           
            
            LocalDate fromDate = date_range1.getValue();
            LocalDate toDate = date_range2.getValue();

            if (selectedStudentName == null || selectedSubjectName == null || fromDate == null || toDate == null) {
                System.out.println("Please select all fields.");
                return;
            }

            try {
                connect = DBConnection.getInstance().getConnection();
                
                String sql =
    "SELECT a.student_id, s.student_name, sub.subjects_name, a.status, a.attendance_date " +
    "FROM student_attendance a " +
    "JOIN student_management s ON a.student_id = s.student_id " +
    "JOIN class_scheduling sh ON a.scheduling_id = sh.scheduling_id " +
    "JOIN subjects sub ON sh.subject_id = sub.subject_id " +
    "WHERE a.student_id = ? " +
      "AND sub.subject_id = ? " +
    "AND a.attendance_date BETWEEN ? AND ?"
                        + "GROUP BY a.student_id, a.attendance_date, a.status";
                
                 PreparedStatement pst = connect.prepareStatement(sql);

                pst.setInt(1, studentid);
                pst.setInt(2, subjectid);
                pst.setDate(3, java.sql.Date.valueOf(fromDate));
                pst.setDate(4, java.sql.Date.valueOf(toDate));

                ResultSet rs = pst.executeQuery();
                   
                 while (rs.next()) {

                    AttendanceReportEntity attendance = new AttendanceReportEntity(
                            rs.getInt("student_id"),
                            rs.getString("student_name"),
                            rs.getString("subjects_name"),
                            rs.getString("attendance_date"),
                            rs.getString("status")
                    );

                    attendance_report_table.getItems().add(attendance);
                }
                } catch (Exception e) {
                e.printStackTrace();
            }

    }
     @FXML
    void attendance_report_table_mouse_clicked(MouseEvent event) {

    }
    @FXML
      void load_combobox2_table() {
         try{
        connect = DBConnection.getInstance().getConnection();
        
        String sql="SELECT subjects_name FROM subjects";
            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             //results add to combo box
             while(rs.next()){
             String value = rs.getString("subjects_name");
             subjectid_combobox.getItems().add(value);
             System.out.println("ok............");
             }
         
         }catch (Exception e) {
        e.printStackTrace();}
     }
    
    @FXML
      void load_combobox1_table() {
         try{
        connect = DBConnection.getInstance().getConnection();
        
        String sql="SELECT student_name FROM student_management";
            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             //results add to combo box
             while(rs.next()){
             String value = rs.getString("student_name");
             studentId_combobox.getItems().add(value);
             System.out.println("ok............");
             }
         
         }catch (Exception e) {
        e.printStackTrace();}
     }
       @FXML
      private int getSubjectIdByName(String selectedSubjectName){
          String checkid="SELECT  subject_id FROM subjects WHERE subjects_name = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, selectedSubjectName);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("subject_id");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return -1;
     }
        @FXML
      private int getStudentIdByName(String selectedStudentName){
          String checkid="SELECT  student_id FROM student_management WHERE student_name = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, selectedStudentName);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("student_id");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return -1;
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load_combobox2_table();
        load_combobox1_table();
       
        student_id_column.setCellValueFactory(new PropertyValueFactory<>("student_id"));
    student_name_column.setCellValueFactory(new PropertyValueFactory<>("student_name"));
    subject_column.setCellValueFactory(new PropertyValueFactory<>("subject"));
    date_range_column.setCellValueFactory(new PropertyValueFactory<>("date"));
    status_column.setCellValueFactory(new PropertyValueFactory<>("status"));

    }    
    
}