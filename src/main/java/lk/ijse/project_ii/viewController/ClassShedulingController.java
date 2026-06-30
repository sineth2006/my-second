package lk.ijse.project_ii.viewController;

import lk.ijse.project_ii.main.App;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
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
    
    @FXML
    void class_sheduling_add_button_OnAction(ActionEvent event) throws SQLException {
            int id=Integer.parseInt(sheduling_id_txt.getText());
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
              }else{
           int courseid=getCourseIdByName(selectedCourseName);//string course_name int course_id(by using "getCourseIdByName"method)
           int subjectid=getSubjectIdByName(selectedSubjectName);
           int lectureid=getLectureIdByName(selectedLectureName);
           
           String checkshedulingid="SELECT * FROM class_scheduling WHERE scheduling_id = ?";
           connect = DBConnection.getInstance().getConnection();

            try{
             prepare=connect.prepareStatement(checkshedulingid);
              prepare.setInt(1, id);
              result=prepare.executeQuery();
              
              if(result.next()){
              alert.errorMessage(id+"is already taken!!!");
              }else{
               String insertdata="INSERT INTO class_scheduling(scheduling_id,course_id,subject_id,lecture_id,class_date,class_time)VALUES(?,?,?,?,?,?)";
              
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, id);
                prepare.setInt(2, courseid);
                prepare.setInt(3, subjectid);
                prepare.setInt(4,lectureid);
                prepare.setString(5,date);
                prepare.setString(6,time);
                
                 int resultCount=prepare.executeUpdate();
               
                 if(resultCount>0){
           alert.successmessage("Updated Successfully!!!");
           ClassShedulingEntity newsclassshule=new ClassShedulingEntity(id,courseid,subjectid,lectureid,date,time);
            class_sheduling_table.getItems().add(newsclassshule);
               
                        newsclassshule.setScheduling_id(id);
                        newsclassshule.setCourse_id(courseid);
                        newsclassshule.setSubject_id(subjectid);
                        newsclassshule.setLecture_id(lectureid);
                        newsclassshule.setClass_date(date);
                        class_sheduling_table.refresh();
           
                  sheduling_id_txt.clear();
                    couse_id_combo.setValue(null);

                  
                 
                  
          }   

              }
            
            } catch (Exception e) {
        e.printStackTrace();}
    
     }
    }
    @FXML
    void class_sheduling_update_button_OnAction(ActionEvent event) throws SQLException {
    int id=Integer.parseInt(sheduling_id_txt.getText());
             String selectedCourseName=couse_id_combo.getSelectionModel().getSelectedItem();
             String selectedSubjectName=subject_id_combo.getSelectionModel().getSelectedItem();
             String selectedLectureName=lecture_id_combo.getSelectionModel().getSelectedItem();
             String date = String.valueOf(class_date.getValue());
             String time=class_time_combo.getSelectionModel().getSelectedItem();
             
             
         connect = DBConnection.getInstance().getConnection();
       if(connect!=null) {
        String sql = "UPDATE class_scheduling SET scheduling_id=?, course_id=?, subject_id=?, lecture_id=? ,class_date=?,class_time=? WHERE scheduling_id =?";
        PreparedStatement stm = connect.prepareStatement(sql);
         int courseid=getCourseIdByName(selectedCourseName);//string course_name int course_id(by using "getCourseIdByName"method)
           int subjectid=getSubjectIdByName(selectedSubjectName);
           int lectureid=getLectureIdByName(selectedLectureName);
           
        stm.setInt(1, id);
        stm.setInt(2, courseid);
        stm.setInt(3, subjectid);
        stm.setInt(4, lectureid);
        stm.setString(5,date );
        stm.setString(6,time );
        stm.setInt(7,id );
          int result = stm.executeUpdate();
         
           if(result>0){
           alert.successmessage("Updated Successfully!!!");
           ClassShedulingEntity selectedclassschedule=class_sheduling_table.getSelectionModel().getSelectedItem();
            if (selectedclassschedule != null) {
                            //ui update
                        selectedclassschedule.setScheduling_id(id);//not in other codes i put
                        selectedclassschedule.setCourse_id(courseid);
                        selectedclassschedule.setSubject_id(subjectid);
                        selectedclassschedule.setLecture_id(lectureid);
                        selectedclassschedule.setClass_date(date);
                        selectedclassschedule.setClass_time(time);
                        class_sheduling_table.refresh();
           } 
                  sheduling_id_txt.clear();
                  couse_id_combo.getSelectionModel().clearSelection();
                  subject_id_combo.getSelectionModel().clearSelection();
                  lecture_id_combo.getSelectionModel().clearSelection();
                   class_date.setValue(null);
                   class_time_combo.getSelectionModel().clearSelection();

                  
          }   

       }
    }
    @FXML
     void class_sheduling_delete_button_OnAction(ActionEvent event) throws SQLException {
             
        int id=Integer.parseInt(sheduling_id_txt.getText());
         connect = DBConnection.getInstance().getConnection();
         
           try{if(connect!=null) {
               
                
                String sql = "DELETE FROM class_scheduling WHERE scheduling_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
                int result = stm.executeUpdate();
               
                if(result>0){
                 alert.successmessage("Successfully deleted!!!");
                   Object selectedItem = class_sheduling_table.getSelectionModel().getSelectedItem();
                class_sheduling_table.getItems().remove(selectedItem);
                
               sheduling_id_txt.clear();
                  couse_id_combo.getSelectionModel().clearSelection();
                  subject_id_combo.getSelectionModel().clearSelection();
                  lecture_id_combo.getSelectionModel().clearSelection();
                   class_date.setValue(null);
                   class_time_combo.getSelectionModel().clearSelection();
                }else{
                        System.out.println("Failed to delected class schedule.");  
                          }
                }
            }catch (Exception e) {
            
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
     private int getCourseIdByName(String selectedCourseName){
          String checkid="SELECT course_id FROM course_management WHERE course_name = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, selectedCourseName);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("course_id");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return -1;
     }
       @FXML
     private String getCourseNameById(int selectedcourseid){
          String checkid="SELECT course_name FROM course_management WHERE course_id = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setInt(1, selectedcourseid);
              result=pst.executeQuery();
               if(result.next()){
                   
                 return result.getString("course_name");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return null;
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
      private String getSubjectNameById(int selectedSubjectid){
          String checkid="SELECT  subjects_name FROM subjects WHERE subject_id = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setInt(1, selectedSubjectid);
              result=pst.executeQuery();
               if(result.next()){
                 
                 return result.getString("subjects_name");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return null;
     }
       @FXML
      private int getLectureIdByName(String selectedLectureName){
          String checkid="SELECT  lecture_id FROM lecture_management WHERE lecture_name = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, selectedLectureName);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("lecture_id");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return -1;
     }
       @FXML
      private String getLectureNameById(int selectedLectureId){
          String checkid="SELECT  lecture_name FROM lecture_management WHERE lecture_id = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setInt(1, selectedLectureId);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getString("lecture_name");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return null;
     }
       @FXML
     void load_combobox1_table() {
         try{
        connect = DBConnection.getInstance().getConnection();
        
        String sql="SELECT course_name FROM course_management";
            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             //results add to combo box
             while(rs.next()){
             String value = rs.getString("course_name");
             couse_id_combo.getItems().add(value);
             System.out.println("ok............");
             }
         
         }catch (Exception e) {
        e.printStackTrace();}
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
             subject_id_combo.getItems().add(value);
             System.out.println("ok............");
             }
         
         }catch (Exception e) {
        e.printStackTrace();}
     }

      @FXML
     void load_combobox3_table() {
         try{
        connect = DBConnection.getInstance().getConnection();
        
        String sql="SELECT lecture_name FROM lecture_management";
            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             //results add to combo box
             while(rs.next()){
             String value = rs.getString("lecture_name");
             lecture_id_combo.getItems().add(value);
             System.out.println("ok............");
             }
         
         }catch (Exception e) {
        e.printStackTrace();}
     }
           @FXML
    public void loadTable() {
   ObservableList<ClassShedulingEntity>schedulingList =FXCollections.observableArrayList();
   
   try {
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM class_scheduling";
        PreparedStatement stm = conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
   while(rs.next()){
       
        schedulingList.add(new ClassShedulingEntity(
                    rs.getInt("scheduling_id"),
                    rs.getInt("course_id"),
                    rs.getInt("subject_id"),
                    rs.getInt("lecture_id"),
                    rs.getString("class_date"),
                    rs.getString("class_time")
        ));
   }
   class_sheduling_table.setItems(schedulingList);
   }catch (Exception e) {
        e.printStackTrace();
    }
    }
      @FXML
    void class_sheduling_table_tableClicked(MouseEvent event) {

           ClassShedulingEntity clickclassshedule=class_sheduling_table.getSelectionModel().getSelectedItem();
        
        String id=Integer.toString(clickclassshedule.getScheduling_id());
        int selectedcourseid=clickclassshedule.getCourse_id();
        int selectedsubjectid=clickclassshedule.getSubject_id();
         int selectedLectureId=clickclassshedule.getLecture_id();
        String date=clickclassshedule.getClass_date();
        String time=clickclassshedule.getClass_time();
        
        String coursename=getCourseNameById(selectedcourseid);
         String subjectname=getSubjectNameById(selectedsubjectid);
          String lecturename=getLectureNameById(selectedLectureId);
          //CONVERT STRINGDATE TO STANDARD DATE
         java.time.LocalDate localDate = java.time.LocalDate.parse(date);
         
          sheduling_id_txt.setText(id);
        couse_id_combo.setValue(coursename);
        subject_id_combo.setValue(subjectname);
         lecture_id_combo.setValue(lecturename);
        class_date.setValue(localDate);
        class_time_combo.setValue(time);
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
        
        load_combobox1_table();
        load_combobox2_table();
        load_combobox3_table();
        loadTable();
    }    
    
}