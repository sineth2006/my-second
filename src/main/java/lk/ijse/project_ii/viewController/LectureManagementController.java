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
    void lecture_managment_add_button_OnAction(ActionEvent event) throws SQLException {
        
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
        
          }else{
          int userid=getUserIdByName(selecteUserName);//string course_name convert int course_id(by using "getCourseIdByName"method)
          int subjectid=getSubjectIdByName(selectedSubjectName);
          String checklecturetid="SELECT * FROM lecture_management WHERE lecture_id = ?";
           connect = DBConnection.getInstance().getConnection();
              try{
             prepare=connect.prepareStatement(checklecturetid);
              prepare.setInt(1, id);
              result=prepare.executeQuery();
              
                if(result.next()){
              alert.errorMessage(id+"is already taken!!!");
              }else{
               String insertdata="INSERT INTO lecture_management(lecture_id,lecture_name,email,telephone_number,subject_id,user_id)VALUES(?,?,?,?,?,?)";
              
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, id);
                prepare.setString(2, name);
                prepare.setString(3,email);
                prepare.setString(4,telephone); 
                prepare.setInt(5,subjectid);
                prepare.setInt(6,userid);
                  int resultCount=prepare.executeUpdate();
                 if(resultCount>0){
                  alert.successmessage("Added Successfully!");
                     //show in UI
                 LectureManagementEntity newlecture=new LectureManagementEntity(id,name,email,telephone,subjectid,userid);
                  lecture_management_table.getItems().add(newlecture);

                  lecture_id.clear();
                  lecture_name.clear();
                  telephone_number.clear();
                  email_txt.clear();
                  
                   } 
              }
              }catch (Exception e) {
        e.printStackTrace();}
    }
    }
     @FXML
    void lecture_managment_update_button_OnAction(ActionEvent event) throws SQLException {
           int id=Integer.parseInt(lecture_id.getText());
         String name=lecture_name.getText();
         String telephone= telephone_number.getText().trim();//remove extra spaces
          String email=email_txt.getText();
         String selecteUserName=user_id.getSelectionModel().getSelectedItem();
         String selectedSubjectName=subject_id.getSelectionModel().getSelectedItem();

         connect = DBConnection.getInstance().getConnection();
       if(connect!=null) {
        String sql = "UPDATE lecture_management SET lecture_id=?, lecture_name=?, email=?, telephone_number=? ,user_id=?,subject_id=? WHERE lecture_id =?";
        PreparedStatement stm = connect.prepareStatement(sql);
          int userid=getUserIdByName(selecteUserName);
          int subjectid=getSubjectIdByName(selectedSubjectName);
        stm.setInt(1, id);
        stm.setString(2, name);
        stm.setString(3, email);
        stm.setString(4, telephone);
        stm.setInt(5,userid );
        stm.setInt(6,subjectid );
        stm.setInt(7,id );
          int result = stm.executeUpdate();
        if(result>0){
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
                  lecture_id.clear();
                  lecture_name.clear();
                  telephone_number.clear();
                  email_txt.clear();

                  
          }   

       }
    }

    @FXML
    void lecture_managment_delete_button_OnAction(ActionEvent event) throws SQLException {
          
        int id=Integer.parseInt(lecture_id.getText());
         connect = DBConnection.getInstance().getConnection();
         
           try{if(connect!=null) {
               
                
                String sql = "DELETE FROM lecture_management WHERE lecture_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
                int result = stm.executeUpdate();
               
                if(result>0){
                 alert.successmessage("Successfully deleted!!!");
                   Object selectedItem = lecture_management_table.getSelectionModel().getSelectedItem();
                lecture_management_table.getItems().remove(selectedItem);
                
               lecture_id.clear();
               lecture_name.clear();
               telephone_number.clear();
                email_txt.clear();
                }else{
                        System.out.println("Failed to delected lecture.");  
                          }
                }
            }catch (Exception e) {
            
            }

    }

     private int getUserIdByName(String selecteUserName){
          String checkid="SELECT  user_id FROM users WHERE user_name = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, selecteUserName);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("user_id");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return -1;
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
     private String getUserNameById(int selectedUserId){
          String checkname="SELECT user_name FROM users WHERE user_id = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkname);
              pst.setInt(1, selectedUserId);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getString("user_name");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return null;
     }
       @FXML
     private String getsubjectNameById(int selectedSubjectId){
          String checkname="SELECT subjects_name FROM subjects WHERE subject_id = ?";
          try{ connect = DBConnection.getInstance().getConnection();
              PreparedStatement pst = connect.prepareStatement(checkname);
              pst.setInt(1, selectedSubjectId);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getString("subjects_name");
               }
               
          }catch (Exception e) {
        e.printStackTrace();}
     return null;
     }


      @FXML
     void load_combobox1_table() {
         try{
        connect = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT user_name FROM users " +
             "INNER JOIN positions ON users.position_id = positions.position_id " +
             "WHERE  positions.position_id = 2";

            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
             //results add to combo box
             while(rs.next()){
             String value = rs.getString("user_name");
             user_id.getItems().add(value);
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
             subject_id.getItems().add(value);
             System.out.println("ok............");
             }
         
         }catch (Exception e) {
        e.printStackTrace();}
     }
      @FXML
    public void loadTable() {
   ObservableList<LectureManagementEntity>lectureList =FXCollections.observableArrayList();
   
   try {
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM lecture_management";
        PreparedStatement stm = conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
   while(rs.next()){
       
        lectureList.add(new LectureManagementEntity(
                    rs.getInt("lecture_id"),
                    rs.getString("lecture_name"),
                     rs.getString("email"),
                    rs.getString("telephone_number"),
                     rs.getInt("subject_id"),
                    rs.getInt("user_id")
        ));
   }
   lecture_management_table.setItems(lectureList);
   }catch (Exception e) {
        e.printStackTrace();
    }
    }
    @FXML
    void lecture_management_mouseclicked(MouseEvent event) {
         LectureManagementEntity clicklecture=lecture_management_table.getSelectionModel().getSelectedItem();
        
        String id=Integer.toString(clicklecture.getLecture_id());
        String name=clicklecture.getLecture_name();
        String telephone=clicklecture.getTelephone_number();
        String Email=clicklecture.getEmail();
        int selectedUserId=clicklecture.getUser_id();
        int selectedsubjectId=clicklecture.getSubject_id();
        
        String username=getUserNameById(selectedUserId);//int coure_id convert string course_name(by using "getCourseNameById"method)
          String subjectname=getsubjectNameById(selectedsubjectId);//int coure_id convert string course_name(by using "getCourseNameById"method)

        lecture_id.setText(id);
        lecture_name.setText(name);
         email_txt.setText(Email);
        telephone_number .setText(telephone);
        user_id.setValue(username);
        subject_id.setValue(subjectname);
    }

    @Override
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
        loadTable();
    }    
    
}