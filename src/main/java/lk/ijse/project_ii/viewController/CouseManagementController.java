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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.bo.CourseBo;
import lk.ijse.project_ii.bo.impl.CourseBoImpl;
import lk.ijse.project_ii.dao.CourseDAO;
import lk.ijse.project_ii.dao.impl.CourseDAOImpl;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.CourseDTO;
import lk.ijse.project_ii.entity.CourseManagementEntity;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class CouseManagementController implements Initializable {
    
private Connection connect;
 private PreparedStatement prepare;
 private ResultSet result;
 private AlertMessege alert=new AlertMessege();
 
    @FXML
    private Button Couse_Management_back_button;
     @FXML
    private TableView<CourseManagementEntity> couse_management_table;
    
    @FXML
    private TextField course_id_txtbox;

    @FXML
    private TextField course_name_txtbox;
    
    @FXML
    private TextField course_duration_txtbox;
      
    @FXML
    private TableColumn<CourseManagementEntity, String> course_duration;

    @FXML
    private TableColumn<CourseManagementEntity, Integer> course_id;

    @FXML
    private TableColumn<CourseManagementEntity, String> course_name;

    @FXML
    private Button couse_management_add_button;

    @FXML
    private Button couse_management_delete_button;

    @FXML
    private Button couse_management_update_button;

      
   private CourseDAO courseDao = new CourseDAOImpl();/////////////////////////////////
    private final CourseBo courseBo = new CourseBoImpl(); 
    
    @FXML
    void Couse_Management_Add_button_OnAction(ActionEvent event) throws SQLException, Exception {
        
            int id=Integer.parseInt(course_id_txtbox.getText());
            String name=course_name_txtbox.getText();
           String duration= course_duration_txtbox.getText();
           
           
           if(course_id_txtbox.getText().isEmpty()
              ||name.isEmpty()
              || duration.isEmpty()){
           
           alert.errorMessage("please fil all the fields!!");
           return;
           } 
           
             if (courseDao.existByName(name)) {
            alert.errorMessage(name + " is already taken!");
            return;
}
                //show database items in UI
                  CourseManagementEntity newcourse=new CourseManagementEntity(id,name,duration);
                    boolean isSaved = courseDao.save(newcourse);
                  if(isSaved){
                      couse_management_table.getItems().add(newcourse);
                  
                    alert.successmessage("Added Successfully!");
                  
                 Clear_txt();
                  
                  }
                  else{
                   System.out.println("Failed to add course table");
                  }
           
    }

    @FXML
    void Couse_Management_Update_button_OnAction(ActionEvent event) throws SQLException, Exception {
           int id=Integer.parseInt(course_id_txtbox.getText());
            String name=course_name_txtbox.getText();
           String duration= course_duration_txtbox.getText();
           
   
        
    boolean isUpdated = courseBo.updateCourse(new CourseDTO(id, name, duration));

            if (isUpdated) {
            alert.successmessage("Updated Successfully!");
          
                CourseManagementEntity selected = couse_management_table.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setCourse_name(name);
                    selected.setCourse_duration(duration);
                    couse_management_table.refresh();
           } 
                 Clear_txt();
          }
      }
    

    @FXML
    void Couse_Management_back_button_OnAction(ActionEvent event) throws IOException {
       
        Parent root=FXMLLoader.load(App.class.getResource("AdminDashboad.fxml"));
        Stage stage=new Stage();
        stage.setTitle("AdminDashboad");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)Couse_Management_back_button.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void Couse_Management_delete_button_OnAction(ActionEvent event) throws SQLException {
          try {
            int id = Integer.parseInt(course_id_txtbox.getText());
            if (courseBo.deleteCourse(id)) {
                 alert.successmessage("Successfully deleted!!!");
                   Object selectedItem = couse_management_table.getSelectionModel().getSelectedItem();
                couse_management_table.getItems().remove(selectedItem);
                
             Clear_txt();
                }
                
            }catch (Exception e) {
            
            }
        
    }
    
   @FXML
    void couse_management_table_clicked(MouseEvent event) {
        
        CourseManagementEntity clickcourse=couse_management_table.getSelectionModel().getSelectedItem();
        
        String id=Integer.toString(clickcourse.getCourse_id());
        String name=clickcourse.getCourse_name();
        String duration=clickcourse.getCourse_duration();
        
        course_id_txtbox.setText(id);
        course_name_txtbox.setText(name);
        course_duration_txtbox .setText(duration);
        
    }
     @FXML
    public void loadTable() {
     ObservableList<CourseManagementEntity> courseList = FXCollections.observableArrayList();
        try {
            for (CourseDTO dto : courseBo.getAllCourses()) {
                courseList.add(new CourseManagementEntity(dto.getId(), dto.getName(), dto.getDuration()));
            }
   couse_management_table.setItems(courseList);
   }catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void Clear_txt(){
    
         course_id_txtbox.clear();
                  course_name_txtbox.clear();
                  course_duration_txtbox.clear();
    
    }
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
       
        course_id.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        course_name.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        course_duration.setCellValueFactory(new PropertyValueFactory<>("course_duration"));
       loadTable();
    }    
    
}