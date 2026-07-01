package lk.ijse.project_ii.viewController;

import lk.ijse.project_ii.main.App;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.bo.ClassSchedulingBo;
import lk.ijse.project_ii.bo.impl.ClassSchedulingBoImpl;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.ClassSchedulingDTO;
import lk.ijse.project_ii.entity.ClassShedulingEntity;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class ClassShedulingController implements Initializable {

    private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private AlertMessege alert=new AlertMessege();
     @FXML
    private DatePicker class_date;

    @FXML
    private Button class_sheduling_add_button;

    @FXML
    private Button class_sheduling_back_button;
    
    @FXML
    private TableView<ClassShedulingEntity> class_sheduling_table;
    
     @FXML
    private TableColumn<ClassShedulingEntity, String> class_date_column;
    
    @FXML
    private TableColumn<ClassShedulingEntity,String> class_time_column;
    
    @FXML
    private TableColumn<ClassShedulingEntity,Integer> course_id_column;
    
     @FXML
    private TableColumn<ClassShedulingEntity,Integer> lecture_id_column;
     
      @FXML
    private TableColumn<ClassShedulingEntity,Integer> sheduling_id_column;
     
    @FXML
    private TableColumn<ClassShedulingEntity,Integer> subject_id_column;
    @FXML
    private ComboBox<String> class_time_combo;

    @FXML
    private ComboBox<String> couse_id_combo;

    @FXML
    private ComboBox<String> lecture_id_combo;

    @FXML
    private TextField sheduling_id_txt;

    @FXML
    private ComboBox<String> subject_id_combo;
  
    @FXML
    private Button class_sheduling_delete_button;

    @FXML
    private Button class_sheduling_update_button;

    @FXML
    private TextField student_id;
    
     private final ClassSchedulingBo schedulingBO = new ClassSchedulingBoImpl();
    @FXML
    void class_sheduling_add_button_OnAction(ActionEvent event) throws SQLException {
            
        try{int id=Integer.parseInt(sheduling_id_txt.getText());
             String selectedCourseName=couse_id_combo.getSelectionModel().getSelectedItem();
             String selectedSubjectName=subject_id_combo.getSelectionModel().getSelectedItem();
             String selectedLectureName=lecture_id_combo.getSelectionModel().getSelectedItem();
             String date = String.valueOf(class_date.getValue());
             String time=class_time_combo.getSelectionModel().getSelectedItem();
           if(sheduling_id_txt.getText().isEmpty()
              ||selectedCourseName.isEmpty()
              || selectedSubjectName.isEmpty()
              ||selectedLectureName.isEmpty()
               ||date.isEmpty()              
                             )
                     {
             alert.errorMessage("please fil all the fields!!");
             return;
              }else{
           int courseId = schedulingBO.getCourseId(selectedCourseName);
            int subjectId = schedulingBO.getSubjectId(selectedSubjectName);
            int lectureId = schedulingBO.getLectureId(selectedLectureName);
           
          ClassSchedulingDTO dto = new ClassSchedulingDTO(id, courseId, subjectId, lectureId, date, time);
            boolean isSaved = schedulingBO.saveSchedule(dto);

           if (isSaved) {
             
           alert.successmessage("Updated Successfully!!!");
           ClassShedulingEntity newsclassshule=new ClassShedulingEntity(id,courseId,subjectId,lectureId,date,time);
            class_sheduling_table.getItems().add(newsclassshule);
               
                   text_clear();
               
          }   

              }
            
            } catch (Exception e) {
        e.printStackTrace();}
    
     }
    
    @FXML
    void class_sheduling_update_button_OnAction(ActionEvent event) throws SQLException, Exception {
  
        int id=Integer.parseInt(sheduling_id_txt.getText());
             String selectedCourseName=couse_id_combo.getSelectionModel().getSelectedItem();
             String selectedSubjectName=subject_id_combo.getSelectionModel().getSelectedItem();
             String selectedLectureName=lecture_id_combo.getSelectionModel().getSelectedItem();
             String date = String.valueOf(class_date.getValue());
             String time=class_time_combo.getSelectionModel().getSelectedItem();
             
              int courseId = schedulingBO.getCourseId(selectedCourseName);
            int subjectId = schedulingBO.getSubjectId(selectedSubjectName);
            int lectureId = schedulingBO.getLectureId(selectedLectureName);
           
            ClassSchedulingDTO dto = new ClassSchedulingDTO(id,courseId,subjectId,lectureId,date,time);
            boolean isUpdated = schedulingBO.updateSchedule(dto);
             if (isUpdated) {
           alert.successmessage("Updated Successfully!!!");
           ClassShedulingEntity selectedEntity=class_sheduling_table.getSelectionModel().getSelectedItem();
            if (selectedEntity != null) {
                            //ui update
                        selectedEntity.setCourse_id(courseId);
                    selectedEntity.setSubject_id(subjectId);
                    selectedEntity.setLecture_id(lectureId);
                    selectedEntity.setClass_date(date);
                    selectedEntity.setClass_time(time);
                    class_sheduling_table.refresh();
                       
           } 
                text_clear();

                  
          }   

       }
    
    @FXML
     void class_sheduling_delete_button_OnAction(ActionEvent event) throws Exception {
             
         int id = Integer.parseInt(sheduling_id_txt.getText());
            boolean isDeleted = schedulingBO.deleteSchedule(id);
            if (isDeleted) {
                 alert.successmessage("Successfully deleted!!!");
                   Object selectedItem = class_sheduling_table.getSelectionModel().getSelectedItem();
                class_sheduling_table.getItems().remove(selectedItem);
                
              text_clear();
                }else{
                        System.out.println("Failed to delected class schedule.");  
                          }
                
          
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
    public void loadTable() {
   ObservableList<ClassShedulingEntity>schedulingList =FXCollections.observableArrayList();
   
   try {
       ArrayList<ClassSchedulingDTO> allSchedules = schedulingBO.getAllSchedules();
         for (ClassSchedulingDTO dto : allSchedules) {
                schedulingList.add(new ClassShedulingEntity(
                    dto.getScheduling_id(),
                    dto.getCourse_id(),
                    dto.getSubject_id(),
                    dto.getLecture_id(),
                    dto.getClass_date(),
                    dto.getClass_time()
                ));
         }
   class_sheduling_table.setItems(schedulingList);
   }catch (Exception e) {
        e.printStackTrace();
    }
    }
      @FXML
    void class_sheduling_table_tableClicked(MouseEvent event) throws Exception {

           ClassShedulingEntity clickclassshedule=class_sheduling_table.getSelectionModel().getSelectedItem();
        
        String id=Integer.toString(clickclassshedule.getScheduling_id());
        String courseName = schedulingBO.getCourseName(clickclassshedule.getCourse_id());
         String subjectName = schedulingBO.getSubjectName(clickclassshedule.getSubject_id());
         String lectureName = schedulingBO.getLectureName(clickclassshedule.getLecture_id());
        String date=clickclassshedule.getClass_date();
        String time=clickclassshedule.getClass_time();
        
        
          //CONVERT STRINGDATE TO STANDARD DATE
         java.time.LocalDate localDate = java.time.LocalDate.parse(date);
         
          sheduling_id_txt.setText(id);
        couse_id_combo.setValue(courseName);
        subject_id_combo.setValue(subjectName);
         lecture_id_combo.setValue(lectureName);
        class_date.setValue(localDate);
        class_time_combo.setValue(time);
    }
    public void text_clear(){
       sheduling_id_txt.clear();
        class_date.setValue(null);
        
        class_time_combo.getSelectionModel().clearSelection();
        subject_id_combo.getSelectionModel().clearSelection();
         lecture_id_combo.getSelectionModel().clearSelection();
    }
     private void load_combobox_data() {
        try {
            ArrayList<String> courseNames = schedulingBO.getCourseNames();
            couse_id_combo.setItems(FXCollections.observableArrayList(courseNames));

            ArrayList<String> lectureNames = schedulingBO.getLectureNames();
            lecture_id_combo.setItems(FXCollections.observableArrayList(lectureNames));

            ArrayList<String> subjectNames = schedulingBO.getSubjectNames();
            subject_id_combo.setItems(FXCollections.observableArrayList(subjectNames));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          sheduling_id_column.setCellValueFactory(new PropertyValueFactory<>("scheduling_id"));
        course_id_column.setCellValueFactory(new PropertyValueFactory<>("course_id"));
      
        subject_id_column.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
       lecture_id_column.setCellValueFactory(new PropertyValueFactory<>("lecture_id"));
        class_date_column.setCellValueFactory(new PropertyValueFactory<>("class_date"));
       class_time_column.setCellValueFactory(new PropertyValueFactory<>("class_time"));
       //manualy put class_time in combobox
        class_time_combo.getItems().clear(); 
        class_time_combo.getItems().addAll("9.00-11.00", "11.00-13.00","13.00-15.00","15.00-17.00");
        
        load_combobox_data();
        loadTable();
    }    
    
}