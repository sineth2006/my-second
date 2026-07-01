package lk.ijse.project_ii.viewController;

import lk.ijse.project_ii.main.App;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import lk.ijse.project_ii.bo.StudentBo;
import lk.ijse.project_ii.bo.impl.StudentBoImpl;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.StudentDTO;
import lk.ijse.project_ii.entity.CourseManagementEntity;
import lk.ijse.project_ii.entity.StudentManagementEntity;


/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class StudentManagementController implements Initializable {

     private AlertMessege alert = new AlertMessege();
    private final StudentBo studentBO = new StudentBoImpl();
    
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
          
           try{  int courseid = studentBO.getCourseId(selectedCourseName);//string course_name int course_id(by using "getCourseIdByName"method)
              StudentDTO dto = new StudentDTO(id, name, telephone, Email, courseid);
              boolean isSaved = studentBO.saveStudent(dto);
                if(isSaved) {
                  alert.successmessage("Added Successfully!");
                  //show in UI
                 StudentManagementEntity newstudent=new StudentManagementEntity(id,name,telephone,Email,courseid);
                  student_management_table.getItems().add(newstudent);

                  clear_txt();
               
        }
            }catch (Exception e) {
        e.printStackTrace();}
        }
    }
    
    
    @FXML
    void student_managment_update_button_OnAction(ActionEvent event) throws SQLException, Exception {
            int id=Integer.parseInt(student_id.getText());
            String name=student_name.getText();
           String telephone= telephone_number.getText();
            String Email= email.getText();
          String selectedCourseName=course_id_combobox.getSelectionModel().getSelectedItem();

            try {
                int courseid = studentBO.getCourseId(selectedCourseName);
                 StudentDTO dto = new StudentDTO(id, name, telephone, Email, courseid);
                
                boolean isUpdated = studentBO.updateStudent(dto);
       
          if(isUpdated) {
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
                  clear_txt();
                  
          }   

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    @FXML
    void student_managment_delete_button_OnAction(ActionEvent event) throws SQLException, Exception {

          int id=Integer.parseInt(student_id.getText());
       ////////////////////connecton
        try{  
            boolean isdeleted=studentBO.deleteStudent(id) ;
            if(isdeleted){ 
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
                
            }catch (Exception e) {
              e.printStackTrace();
            }

    }

     @FXML
     void load_combobox_table() {
         try{
         ArrayList<String> courseNames = studentBO.getCourseNames();
             course_id_combobox.setItems(FXCollections.observableArrayList(courseNames));
         
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
        List<StudentDTO> allStudents = studentBO.getAllStudents();
            
            for (StudentDTO dto : allStudents) {
                studentList.add(new StudentManagementEntity(
                    dto.getStudent_id(),
                    dto.getStudent_name(),
                    dto.getTelephone_number(),
                    dto.getEmail(),
                    dto.getCourse_id()
                ));
   }
   student_management_table.setItems(studentList);
   }catch (Exception e) {
        e.printStackTrace();
    }
    }

   
      @FXML
private String getCourseNameById(int selectedCourseId) {
    try {
       
        return studentBO.getCourseName(selectedCourseId);
    } catch (Exception e) {
        e.printStackTrace();
    }
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
    public void clear_txt(){
    
    student_id.clear();
      email.clear();
     telephone_number.clear();
    student_name.clear();
                  
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