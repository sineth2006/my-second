package lk.ijse.project_ii.viewController;

import lk.ijse.project_ii.main.App;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;

/**
 * FXML Controller class
 *
 * @author Sineth
 */

 
public class LogingUIController implements Initializable {

 private Connection connect;
 private PreparedStatement prepare;
 private ResultSet result;
 private AlertMessege alert=new AlertMessege();
  
  
    @FXML
    private Button loging_button;

    @FXML
    private CheckBox loging_chechbox;

    @FXML
    private AnchorPane loging_form;

    @FXML
    private PasswordField loging_password;

    @FXML
    private Hyperlink loging_registerhere;

    @FXML
    private ComboBox<String> loging_user;

    @FXML
    private TextField loging_username;

    @FXML
    private AnchorPane mainform;

    @FXML
    private Button register_button;

    @FXML
    private CheckBox register_checkbox;

    @FXML
    private Hyperlink register_clickhere;

    @FXML
    private AnchorPane register_form;

    @FXML
    private PasswordField register_password;

    @FXML
    private ComboBox<String> register_user;

    @FXML
    private TextField register_username;
    
    @FXML
    private TextField register_showpassword;
    
      @FXML
    private TextField loging_showpassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Option A: Clear existing defaults and add items one by one
        loging_user.getItems().clear(); 
        loging_user.getItems().addAll("admin", "lecture");
        
        register_user.getItems().clear();
        register_user.getItems().addAll("admin", "lecture");
    }
     @FXML
    void loging_button_OnAction(ActionEvent event)throws IOException, SQLException {
        
        
        String actualpassword="";
        if(loging_showpassword !=null && loging_showpassword.isVisible()){
            actualpassword=loging_showpassword.getText();
        }else{
            actualpassword=loging_password.getText();
        }
        if(loging_username.getText().isEmpty()||
                loging_password.getText().isEmpty() ||
                actualpassword.isEmpty() ){
        alert.errorMessage("Please fill all fields");
        }
        else{
            //i write inner join for check the database specially with position id
            String sql=
              "SELECT * FROM users " +
             "INNER JOIN positions ON users.position_id = positions.position_id " +
             "WHERE users.user_name = ? AND users.password = ? AND positions.position_name = ?";
            connect=DBConnection.getInstance().getConnection();
            
            try{
                
                prepare=connect.prepareStatement(sql);
                prepare.setString(1,loging_username.getText());
                prepare.setString(2,actualpassword);
                prepare.setString(3, loging_user.getSelectionModel().getSelectedItem());
                result=prepare.executeQuery();
                
                if(result.next()){
                    //if correct username and password
                alert.successmessage("Loging Successfully!");
                registerformclear();
                //click logigng button go to lecturedashboad or admin dashboad
                   String loggedInRole = result.getString("position_name"); 
                if(loggedInRole.equals("admin")){
                  loging_form.setVisible(false);
                  
                  
                //        String username=loging_username.getText();
//        String password=new String(loging_password.getpassword );
//admin dashboad
    //use for back button to select whatr UI use
        App.currentUserRole = "Admin";
        
        Parent root=FXMLLoader.load(App.class.getResource("AdminDashboad.fxml"));
        Stage stage=new Stage();
        stage.setTitle("AdminDashboad");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)loging_button.getScene().getWindow();
        currentStage.close();
        System.out.println("Hello...........123654");
                }else{
                    //lecturedashboad
        //use for back button to select whatr UI use
         App.currentUserRole = "Lecturer";
           
        Parent root=FXMLLoader.load(App.class.getResource("LectureDashboad.fxml"));
        Stage stage=new Stage();
        stage.setTitle("AdminDashboad");
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage=(Stage)loging_button.getScene().getWindow();
        currentStage.close();
                }
                }else{
                alert.errorMessage("Incorrect Usrname/Password/Positon");
                }
            }catch(Exception e){
             e.printStackTrace();}
        }
        
         
         

    }
    
    
    
   
    
     @FXML 
    public void registerAccount() throws IOException, SQLException {
        
        if(register_username.getText().isEmpty()
                ||register_password.getText().isEmpty()
                 || register_user.getSelectionModel().getSelectedItem() == null)
        {
                alert.errorMessage("Please fill all blank fields");
                return;
                
        }else{
            String checkusername="SELECT * FROM users WHERE user_name = ?";
           connect = DBConnection.getInstance().getConnection();
           
            try{
               
                prepare=connect.prepareStatement(checkusername);
                 prepare.setString(1, register_username.getText());
                result=prepare.executeQuery();
                
            if(result.next()){
                alert.errorMessage(register_username.getText()+"is already exist!");
            }else if( register_password.getText().length()<5){
            alert.errorMessage("Invalid Password,at least  5 characters needed!");
            }
            else{
                //inserrt data to database
               String selectedrole = register_user.getSelectionModel().getSelectedItem().toString();

                int positionId = -1;
                 //i have take two database checks because checkbox and other check get seperated
                   String getPositionIdSQL = "SELECT position_id FROM positions WHERE position_name = ?";
                  PreparedStatement prepPosition = connect.prepareStatement(getPositionIdSQL);
                   prepPosition.setString(1, selectedrole);
            ResultSet rsPosition = prepPosition.executeQuery();
            if (rsPosition.next()) {
                positionId = rsPosition.getInt("position_id");
            }
            rsPosition.close();
            prepPosition.close();
                 
                String insertdata="INSERT INTO users(user_name,password,position_id)VALUES(?,?,?)";
                
                prepare=connect.prepareStatement(insertdata);
                prepare.setString(1, register_username.getText());
                  prepare.setString(2, register_password.getText());
                  prepare.setInt(3, positionId); 
                  prepare.executeUpdate();
                  alert.successmessage("Registerd Successfully!");
                  registerformclear();
                  //to switch the register form intop loging form
                  loging_form.setVisible(true);
                  register_form.setVisible(false);
                  
            }
            }catch(Exception e){
             e.printStackTrace();}
        }
        
    }
    @FXML 
    public void registershowpassword(){
       if(register_checkbox.isSelected()){
           register_showpassword.setText(register_password.getText());
           register_showpassword.setVisible(true);
           register_password.setVisible(false);
       }else{
        register_password.setText( register_showpassword.getText());
           register_showpassword.setVisible(false);
           register_password.setVisible(true);
       
       }
    
    
    }
     @FXML 
    public void logingshowpassword(){
       if(loging_chechbox.isSelected()){
           loging_showpassword.setText(loging_password.getText());
           loging_showpassword.setVisible(true);
           loging_password.setVisible(false);
       }else{
        loging_password.setText( loging_showpassword.getText());
           loging_showpassword.setVisible(false);
           loging_password.setVisible(true);
       
       }
    
    
    }
    public void registerformclear(){
    register_showpassword.clear();
    register_password.clear();
    register_username.clear();
    
    }
    
    public void switchForm(ActionEvent event){
        if(event.getSource()==loging_registerhere){
    //registration form will show
    loging_form.setVisible(false);
    register_form.setVisible(true);
    }else if(event.getSource()==register_clickhere){
    //loging form will show
     loging_form.setVisible(true);
    register_form.setVisible(false);
    }
    
    }

       
    
}