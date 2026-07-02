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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.bo.LectureManagementBo;
import lk.ijse.project_ii.bo.impl.LectureManagementBoImpl;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.LectureDTO;
import lk.ijse.project_ii.entity.LectureManagementEntity;
import lk.ijse.project_ii.entity.SubjectManagementEntity;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class LectureManagementController implements Initializable {

    private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private AlertMessege alert=new AlertMessege();
   
    private final LectureManagementBo lectureBO = new LectureManagementBoImpl();

    @FXML
    private TextField lecture_id;

    @FXML
    private Button lecture_management_back_button;

    @FXML
    private TextField lecture_name;
    
      @FXML
    private TextField email_txt;
        
    @FXML
    private Button student_mamagment_add_button;

    @FXML
    private Button student_mamagment_delete_button;

    @FXML
    private Button student_mamagment_update_button;
    
     @FXML
    private TableColumn<LectureManagementEntity, Integer> lecture_id_column;
     
     @FXML
    private TableView<LectureManagementEntity> lecture_management_table;
     
     @FXML
    private TableColumn<LectureManagementEntity, String> lecture_name_column;
     
     @FXML
    private TableColumn<LectureManagementEntity, String> email_column;

    @FXML
    private TableColumn<LectureManagementEntity, Integer> subject_id_column;

    @FXML
    private TableColumn<LectureManagementEntity, String> telephone_column;
    
    @FXML
    private TableColumn<LectureManagementEntity, Integer> user_id_column;
     
    @FXML
    private ComboBox<String> subject_id;

    @FXML
    private TextField telephone_number;

    @FXML
    private ComboBox<String> user_id;
     @FXML
    void lecture_management_back_button_OnAction(ActionEvent event) throws IOException {

           
         Parent root=FXMLLoader.load(App.class.getResource("AdminDashboad.fxml"));
        Stage stage=new Stage();
        stage.setTitle("AdminDashboad");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)lecture_management_back_button.getScene().getWindow();
        currentStage.close();
    }

      @FXML
    void lecture_managment_add_button_OnAction(ActionEvent event) throws SQLException, Exception {
        
     
          int id=Integer.parseInt(lecture_id.getText());
         String name=lecture_name.getText();
         String telephone= telephone_number.getText().trim();//remove extra spaces
          String email=email_txt.getText();
         String selecteUserName=user_id.getSelectionModel().getSelectedItem();
         String selectedSubjectName=subject_id.getSelectionModel().getSelectedItem();

            if(lecture_id.getText().isEmpty()
              ||name.isEmpty()
              || telephone.isEmpty()
              ||selecteUserName.isEmpty()
               ||selectedSubjectName.isEmpty())
        {
             alert.errorMessage("please fil all the fields!!");
             return;
        }else{
           int userid = lectureBO.getUserIdByName(selecteUserName);
            int subjectid = lectureBO.getSubjectIdByName(selectedSubjectName);
         
             LectureDTO newlecture = new LectureDTO(id, name, email, telephone, subjectid, userid);
            boolean isSaved = lectureBO.saveLecture(newlecture);
            
                    if (isSaved) {
                    alert.successmessage("Added Successfully!");
                      LectureManagementEntity newslecturer=new LectureManagementEntity(id, name, email, telephone, subjectid, userid);
                     lecture_management_table.getItems().add(newslecturer);
                    
                     clear();
                  
                   } 
              
    }
    }
     @FXML
    void lecture_managment_update_button_OnAction(ActionEvent event) throws SQLException, Exception {
           int id=Integer.parseInt(lecture_id.getText());
         String name=lecture_name.getText();
         String telephone= telephone_number.getText().trim();//remove extra spaces
          String email=email_txt.getText();
         String selecteUserName=user_id.getSelectionModel().getSelectedItem();
         String selectedSubjectName=subject_id.getSelectionModel().getSelectedItem();

       
         int userid = lectureBO.getUserIdByName(selecteUserName);
            int subjectid = lectureBO.getSubjectIdByName(selectedSubjectName);

            LectureDTO updatedLecture = new LectureDTO(id, name, email, telephone, subjectid, userid);
            boolean isUpdated = lectureBO.updateLecture(updatedLecture);
            
             if (isUpdated) { 
           alert.successmessage("Updated Successfully!!!");
           LectureManagementEntity selectedlecture=lecture_management_table.getSelectionModel().getSelectedItem();
           if (selectedlecture != null) {
               
                        selectedlecture.setLecture_name(name);
                        selectedlecture.setTelephone_number(telephone);
                        selectedlecture.setEmail(email);
                        selectedlecture.setTelephone_number(telephone);
                        selectedlecture.setUser_id(userid);
                        selectedlecture.setSubject_id(subjectid);
                        lecture_management_table.refresh();
           } 
                 clear();

                  
          }   

       }
    

    @FXML
    void lecture_managment_delete_button_OnAction(ActionEvent event) throws SQLException {
          
       try{ int id=Integer.parseInt(lecture_id.getText());
           boolean isDeleted = lectureBO.deleteLecture(id);
         
           if (isDeleted) {
                 alert.successmessage("Successfully deleted!!!");
                   Object selectedItem = lecture_management_table.getSelectionModel().getSelectedItem();
                lecture_management_table.getItems().remove(selectedItem);
              clear();
                }else{
                        System.out.println("Failed to delected lecture.");  
                          }
                
            }catch (Exception e) {
            e.printStackTrace();
            }

    }

    
       
      @FXML
     void load_combobox1_table() {
         try {
            ArrayList<String> userNames = lectureBO.getAllUserNamesByPosition(2);
            for (String value : userNames) {
                user_id.getItems().add(value);
                System.out.println("ok............");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
      @FXML
     void load_combobox2_table() {
        
          try {
            ArrayList<String> subjectNames = lectureBO.getAllSubjectNames();
            for (String value : subjectNames) {
                subject_id.getItems().add(value);
                System.out.println("ok............");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
     
     
    @FXML
    public void loadTable() throws Exception {
        ArrayList<LectureDTO> dtoList = lectureBO.getAllLectures();

        ObservableList<LectureManagementEntity> list = FXCollections.observableArrayList();

        for (LectureDTO dto : dtoList) {
            list.add(new LectureManagementEntity(
                    dto.getLecture_id(),
                    dto.getLecture_name(),
                    dto.getEmail(),
                    dto.getTelephone_number(),
                    dto.getSubject_id(),
                    dto.getUser_id()
            ));
        }

        lecture_management_table.setItems(list);
    }
    @FXML
    void lecture_management_mouseclicked(MouseEvent event) {
        LectureManagementEntity clicklecture =
                lecture_management_table.getSelectionModel().getSelectedItem();

        if (clicklecture == null) {
            return; // prevent crash
        }

        lecture_id.setText(String.valueOf(clicklecture.getLecture_id()));
        lecture_name.setText(clicklecture.getLecture_name());
        email_txt.setText(clicklecture.getEmail());
        telephone_number.setText(clicklecture.getTelephone_number());

        try {
            String username = lectureBO.getUserNameById(clicklecture.getUser_id());
            String subjectname = lectureBO.getSubjectNameById(clicklecture.getSubject_id());

            user_id.setValue(username);
            subject_id.setValue(subjectname);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clear() {
        lecture_id.clear();
        lecture_name.clear();
        email_txt.clear();
        telephone_number.clear();
        user_id.setValue(null);
        subject_id.setValue(null);
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           lecture_id_column.setCellValueFactory(new PropertyValueFactory<>("lecture_id"));
        lecture_name_column.setCellValueFactory(new PropertyValueFactory<>("lecture_name"));
      
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
       telephone_column.setCellValueFactory(new PropertyValueFactory<>("telephone_number"));
        subject_id_column.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
       user_id_column.setCellValueFactory(new PropertyValueFactory<>("user_id"));
       
        load_combobox1_table();
        load_combobox2_table();
        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();}
    }    
    
}