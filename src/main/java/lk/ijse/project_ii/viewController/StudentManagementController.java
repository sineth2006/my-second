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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.entity.CourseManagementEntity;
import lk.ijse.project_ii.entity.StudentManagementEntity;


/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class StudentManagementController implements Initializable {

   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private AlertMessege alert=new AlertMessege();
    
    @FXML
    private ComboBox<String> course_id_combobox;

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
    private TableColumn<StudentManagementEntity,String> telephone_number_column;
    
     @FXML
    private TableColumn<StudentManagementEntity,String> student_name_column;
    @FXML
    private TableColumn<StudentManagementEntity, Integer> course_id_column;
    
     @FXML
    private TableColumn<StudentManagementEntity, String> email_column;

    @FXML
    private TableColumn<StudentManagementEntity, Integer> student_id_column;
    
        @FXML
    private TableView<StudentManagementEntity> student_management_table;
        
    @FXML
    private TextField telephone_number;
      @FXML
    void student_managment_add_button_OnAction(ActionEvent event) throws SQLException {
         int id=Integer.parseInt(student_id.getText());
         String name=student_name.getText();
         String telephone= telephone_number.getText().trim();//remove extra spaces
         String Email= email.getText();
         String selectedCourseName=course_id_combobox.getSelectionModel().getSelectedItem();
        if(student_id.getText().isEmpty()
              ||name.isEmpty()
              || telephone.isEmpty()
              ||Email.isEmpty())
        {
             alert.errorMessage("please fil all the fields!!");
        
        
    }else{
           int courseid=getCourseIdByName(selectedCourseName);//string course_name int course_id(by using "getCourseIdByName"method)
        String checkstudentid="SELECT * FROM student_management WHERE student_id = ?";
           connect = DBConnection.getInstance().getConnection();

            try{
             prepare=connect.prepareStatement(checkstudentid);
              prepare.setInt(1, id);
              result=prepare.executeQuery();
              
              if(result.next()){
              alert.errorMessage(id+"is already taken!!!");
              }else{
               String insertdata="INSERT INTO student_management(student_id,student_name,telephone_number,email,course_id)VALUES(?,?,?,?,?)";
              
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, id);
                prepare.setString(2, name);
                prepare.setString(3, telephone);
                prepare.setString(4,Email);
                prepare.setInt(5,courseid);
                 int resultCount=prepare.executeUpdate();
                 if(resultCount>0){
                  alert.successmessage("Added Successfully!");
                  //show in UI
                 StudentManagementEntity newstudent=new StudentManagementEntity(id,name,telephone,Email,courseid);
                  student_management_table.getItems().add(newstudent);

                  student_id.clear();
                  email.clear();
                  telephone_number.clear();
                  student_name.clear();
                  
                 } 
              }
            }catch (Exception e) {
        e.printStackTrace();}
        }
    }
    
    @FXML
    void student_managment_update_button_OnAction(ActionEvent event) throws SQLException {
            int id=Integer.parseInt(student_id.getText());
            String name=student_name.getText();
           String telephone= telephone_number.getText();
            String Email= email.getText();
          String selectedCourseName=course_id_combobox.getSelectionModel().getSelectedItem();

           
      connect = DBConnection.getInstance().getConnection();
       if(connect!=null) {
        String sql = "UPDATE student_management SET student_id=?, student_name=?, telephone_number=?, email=? ,course_id=? WHERE student_id =?";
        PreparedStatement stm = connect.prepareStatement(sql);
          int courseid=getCourseIdByName(selectedCourseName);
        stm.setInt(1, id);
        stm.setString(2, name);
        stm.setString(3, telephone);
        stm.setString(4, Email);
       
        stm.setInt(5, courseid);
        stm.setInt(6, id);
         
          int result = stm.executeUpdate();
        if(result>0){
           alert.successmessage("Updated Successfully!!!");
           StudentManagementEntity selectedstudent=student_management_table.getSelectionModel().getSelectedItem();
           if (selectedstudent != null) {
               
                        selectedstudent.setStudent_name(name);
                        selectedstudent.setTelephone_number(telephone);
                         selectedstudent.setEmail(Email);
                          selectedstudent.setTelephone_number(telephone);
                            selectedstudent.setCourse_id(courseid);
                        student_management_table.refresh();
           } 
                  student_id.clear();
                  student_name.clear();
                  telephone_number.clear();
                  email.clear();
                  
          }   

      
      
    }
    }
    @FXML
    void student_managment_delete_button_OnAction(ActionEvent event) throws SQLException {

          int id=Integer.parseInt(student_id.getText());
         connect = DBConnection.getInstance().getConnection();
         
           try{if(connect!=null) {
               
                
                String sql = "DELETE FROM student_management WHERE student_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
                int result = stm.executeUpdate();
               
                if(result>0){
                 alert.successmessage("Successfully deleted!!!");
                   Object selectedItem = student_management_table.getSelectionModel().getSelectedItem();
                student_management_table.getItems().remove(selectedItem);
                
                 student_id.clear();
                  student_name.clear();
                  telephone_number.clear();
                  email.clear();
                }else{
                        System.out.println("Failed to delected student.");  
                          }
                }
            }catch (Exception e) {
            
            }

    }

     @FXML
     void load_combobox_table() {
         try{
        connect = DBConnection.getInstance().getConnection();
        
        String sql="SELECT course_name FROM course_management";
            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             //results add to combo box
             while(rs.next()){
             String value = rs.getString("course_name");
             course_id_combobox.getItems().add(value);
             System.out.println("ok............");
             }
         
         }catch (Exception e) {
        e.printStackTrace();}
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
      @FXML
    public void loadTable() {
   ObservableList<StudentManagementEntity>studentList =FXCollections.observableArrayList();
   
   try {
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM student_management";
        PreparedStatement stm = conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
   while(rs.next()){
       
        studentList.add(new StudentManagementEntity(
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("telephone_number"),
                    rs.getString("email"),
                    rs.getInt("course_id")
        ));
   }
   student_management_table.setItems(studentList);
   }catch (Exception e) {
        e.printStackTrace();
    }
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
     private String getCourseNameById(int selectedCourseId){
          String checkname="SELECT course_name FROM course_management WHERE course_id = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkname);
              pst.setInt(1, selectedCourseId);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getString("course_name");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return null;
     }
     @FXML
    void student_management_table_clicked(MouseEvent event) {
        
        StudentManagementEntity clickstudent=student_management_table.getSelectionModel().getSelectedItem();
        
        String id=Integer.toString(clickstudent.getStudent_id());
        String name=clickstudent.getStudent_name();
        String telephone=clickstudent.getTelephone_number();
        String Email=clickstudent.getEmail();
        int selectedCourseId=clickstudent.getCourse_id();
        
        String coursename=getCourseNameById(selectedCourseId);//int coure_id convert string course_name(by using "getCourseNameById"method)
        
        student_id.setText(id);
        student_name.setText(name);
        telephone_number .setText(telephone);
        email.setText(Email);
        course_id_combobox.setValue(coursename);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        student_id_column.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        student_name_column.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        telephone_number_column.setCellValueFactory(new PropertyValueFactory<>("telephone_number"));
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        course_id_column.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        load_combobox_table();
        loadTable();
    }    
    
}