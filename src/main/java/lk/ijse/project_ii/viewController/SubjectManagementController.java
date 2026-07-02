/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.project_ii.viewController;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.bo.SubjectBo;
import lk.ijse.project_ii.bo.impl.SubjectBoImpl;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.SubjectDTO;
import lk.ijse.project_ii.entity.SubjectManagementEntity;
import lk.ijse.project_ii.main.App;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class SubjectManagementController implements Initializable {
    
    private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private AlertMessege alert=new AlertMessege();
    private final SubjectBo subjectBO = new SubjectBoImpl();
    @FXML
    private TableColumn<SubjectManagementEntity,Integer> course_id_column;

    @FXML
    private ComboBox<String> course_id_combobox;

    @FXML
    private TableColumn<SubjectManagementEntity, Integer> subject_id_column;

    @FXML
    private Button subject_management_back_button;
    
    @FXML
    private TextField subject_id;
     
     @FXML
    private TextField subject_name;
      
    @FXML
    private TableView<SubjectManagementEntity> subject_management_table;

    @FXML
    private Button subject_managment_add_button;

    @FXML
    private Button subject_managment_delete_button;

    @FXML
    private Button subject_managment_update_button;

    @FXML
    private TableColumn<SubjectManagementEntity,String> subject_name_column;

    @FXML
    void subject_management_back_button_OnAction(ActionEvent event) throws IOException {
           Parent root=FXMLLoader.load(App.class.getResource("AdminDashboad.fxml"));
        Stage stage=new Stage();
        stage.setTitle("AdminDashboad");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)subject_management_back_button.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void subject_managment_add_button_OnAction(ActionEvent event) throws SQLException {
          int id=Integer.parseInt(subject_id.getText());
            String name=subject_name.getText();
             String selectedCourseName=course_id_combobox.getSelectionModel().getSelectedItem();
             
              if(subject_id.getText().isEmpty()
              ||name.isEmpty())
        {
             alert.errorMessage("please fil all the fields!!");
        }else{
                   int courseId = subjectBO.getCourseIdByName(selectedCourseName);
                   SubjectDTO dto = new SubjectDTO(id, name, courseId);
                   boolean isadd=subjectBO.addSubject(dto);
//     
          if(isadd){
                alert.successmessage("Added Successfully!");
                  //show in UI
                 SubjectManagementEntity newsubject=new SubjectManagementEntity(id,name,courseId);
                  subject_management_table.getItems().add(newsubject);

                  subject_id.clear();
                  subject_name.clear();
                   } else{
          
          alert.errorMessage("can't add data");
          }
              }
          

    }
    
     @FXML
    void subject_managment_update_button_OnAction(ActionEvent event) throws SQLException {
            int id=Integer.parseInt(subject_id.getText());
            String name=subject_name.getText();
            String selectedCourseName=course_id_combobox.getSelectionModel().getSelectedItem();

        if (subject_id.getText().isEmpty() || subject_name.getText().isEmpty() || course_id_combobox.getSelectionModel().isEmpty()) {
            alert.errorMessage("Fields cannot be empty for updating!");
            return;
        }
        else{
             int courseId = subjectBO.getCourseIdByName(selectedCourseName);

            SubjectDTO dto = new SubjectDTO(id, name, courseId);
           SubjectManagementEntity selectedsubject=subject_management_table.getSelectionModel().getSelectedItem();
           if (selectedsubject != null) {
               
                        selectedsubject.setSubject_name(name);
                         selectedsubject.setCourse_id(courseId);
                        subject_management_table.refresh();
           
                  subject_id.clear();
                  subject_name.clear();
                  
                  
          }   
       }
    }
    @FXML
    void subject_managment_delete_button_OnAction(ActionEvent event) throws SQLException {
        
          int id=Integer.parseInt(subject_id.getText());
         
           if (subjectBO.deleteSubject(id)) {
                 alert.successmessage("Successfully deleted!!!");
                  SubjectManagementEntity selectedItem = subject_management_table.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    subject_management_table.getItems().remove(selectedItem);
                }
                
                   subject_id.clear();
                  subject_name.clear();  
                  
                }else{
                        System.out.println("Failed to delected subject.");  
                          }
               

    }

    
  @FXML
    public void loadTable() {
   ObservableList<SubjectManagementEntity> subjectList = FXCollections.observableArrayList();
        try {
            // 1. Fetch data arrays downstream from business logic layer DTOs
            ArrayList<SubjectDTO> allSubjects = subjectBO.getAllSubjects();
            
            // 2. Map data tier objects into presentation table view model items
            for (SubjectDTO dto : allSubjects) {
                subjectList.add(new SubjectManagementEntity(
                    dto.getSubject_id(), 
                    dto.getSubject_name(), 
                    dto.getCourse_id()
                ));
            }
            
            // 3. Bind collection items smoothly onto presentation grid layout
            subject_management_table.setItems(subjectList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
     @FXML
    void subject_management_table_clicked(MouseEvent event) {
          
          SubjectManagementEntity clicksubject = subject_management_table.getSelectionModel().getSelectedItem();
        
        // Null check validation prevents runtime errors if an empty grid space is clicked
        if (clicksubject != null) {
            try {
                // 2. Extract internal layout properties out of target entity item
                String id = Integer.toString(clicksubject.getSubject_id());
                String name = clicksubject.getSubject_name();
                int selectedCourseId = clicksubject.getCourse_id();
                
                // 3. Delegate ID-to-Name conversion down to the business layer
                String coursename = subjectBO.getCourseNameById(selectedCourseId);
                
                // 4. Safely populate interactive user text fields and dropdowns on screen
                subject_id.setText(id);
                subject_name.setText(name);
                course_id_combobox.setValue(coursename);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
      @FXML
    public void load_combobox_table() {
        try {
            
            course_id_combobox.getItems().clear();
            
            
            ArrayList<String> courseNamesList = subjectBO.getCourseNames();
            
           
            course_id_combobox.setItems(FXCollections.observableArrayList(courseNamesList));
            
            System.out.println("ok............");
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Failed to load course options to selection drop-down list.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         subject_id_column.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
        subject_name_column.setCellValueFactory(new PropertyValueFactory<>("subject_name"));
      
        course_id_column.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        
        loadTable();
        load_combobox_table();
    }    
    
}
