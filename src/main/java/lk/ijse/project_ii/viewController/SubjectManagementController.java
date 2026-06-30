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
           int courseid=getCourseIdByName(selectedCourseName);//string course_name convert int course_id(by using "getCourseIdByName"method)
        String checksubjectid="SELECT * FROM subjects WHERE subject_id = ?";
           connect = DBConnection.getInstance().getConnection();
              try{
             prepare=connect.prepareStatement(checksubjectid);
              prepare.setInt(1, id);
              result=prepare.executeQuery();
              
              if(result.next()){
              alert.errorMessage(id+"is already taken!!!");
              }else{
               String insertdata="INSERT INTO subjects(subject_id,subjects_name,course_id)VALUES(?,?,?)";
              
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, id);
                prepare.setString(2, name);
                  prepare.setInt(3,courseid);
                  int resultCount=prepare.executeUpdate();
                 if(resultCount>0){
                  alert.successmessage("Added Successfully!");
                  //show in UI
                 SubjectManagementEntity newsubject=new SubjectManagementEntity(id,name,courseid);
                  subject_management_table.getItems().add(newsubject);

                  subject_id.clear();
                  subject_name.clear();
                   } 
              }
            }catch (Exception e) {
        e.printStackTrace();}
          }

    }
    
     @FXML
    void subject_managment_update_button_OnAction(ActionEvent event) throws SQLException {
            int id=Integer.parseInt(subject_id.getText());
            String name=subject_name.getText();
            String selectedCourseName=course_id_combobox.getSelectionModel().getSelectedItem();

           connect = DBConnection.getInstance().getConnection();
       if(connect!=null) {
        String sql = "UPDATE subjects SET subject_id=?,subjects_name=? ,course_id=? WHERE subject_id =?";
        PreparedStatement stm = connect.prepareStatement(sql);
          int courseid=getCourseIdByName(selectedCourseName);
        stm.setInt(1, id);
        stm.setString(2, name);
         stm.setInt(3, courseid);
        stm.setInt(4, id);
         
          int result = stm.executeUpdate();
         if(result>0){
           alert.successmessage("Updated Successfully!!!");
           SubjectManagementEntity selectedsubject=subject_management_table.getSelectionModel().getSelectedItem();
           if (selectedsubject != null) {
               
                        selectedsubject.setSubject_name(name);
                         selectedsubject.setCourse_id(courseid);
                        subject_management_table.refresh();
           } 
                  subject_id.clear();
                  subject_name.clear();
                  
                  
          }   
       }
    }
    @FXML
    void subject_managment_delete_button_OnAction(ActionEvent event) throws SQLException {
        
          int id=Integer.parseInt(subject_id.getText());
         connect = DBConnection.getInstance().getConnection();
         
           try{if(connect!=null) {
               
                
                String sql = "DELETE FROM subjects WHERE subject_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
                int result = stm.executeUpdate();
               
                if(result>0){
                 alert.successmessage("Successfully deleted!!!");
                   Object selectedItem = subject_management_table.getSelectionModel().getSelectedItem();
                subject_management_table.getItems().remove(selectedItem);
                
                   subject_id.clear();
                  subject_name.clear();  
                  
                }else{
                        System.out.println("Failed to delected subject.");  
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
    public void loadTable() {
   ObservableList<SubjectManagementEntity>subjecttList =FXCollections.observableArrayList();
   
   try {
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM subjects";
        PreparedStatement stm = conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
   while(rs.next()){
       
        subjecttList.add(new SubjectManagementEntity(
                    rs.getInt("subject_id"),
                    rs.getString("subjects_name"),
                    rs.getInt("course_id")
        ));
   }
   subject_management_table.setItems(subjecttList);
   }catch (Exception e) {
        e.printStackTrace();
    }
    }
     @FXML
    void subject_management_table_clicked(MouseEvent event) {
          
            SubjectManagementEntity clicksubject=subject_management_table.getSelectionModel().getSelectedItem();
        
        String id=Integer.toString(clicksubject.getSubject_id());
        String name=clicksubject.getSubject_name();
        int selectedCourseId=clicksubject.getCourse_id();
        
        String coursename=getCourseNameById(selectedCourseId);//int coure_id convert string course_name(by using "getCourseNameById"method)
        
        subject_id.setText(id);
        subject_name.setText(name);
        course_id_combobox.setValue(coursename);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         subject_id_column.setCellValueFactory(new PropertyValueFactory<>("subject_id"));
        subject_name_column.setCellValueFactory(new PropertyValueFactory<>("subject_name"));
      
        course_id_column.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        load_combobox_table();
        loadTable();
    }    
    
}
