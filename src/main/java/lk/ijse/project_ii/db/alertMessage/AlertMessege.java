package lk.ijse.project_ii.db.alertMessage;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Sineth
 */
public class AlertMessege {

    private Alert alert;

    public void errorMessage(String message) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
    public void successmessage(String message){
    
     alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("information Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    
    
    }
     public boolean conformationmessage(String message){
    
     alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Conformation Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
       
        Optional<ButtonType> option=alert.showAndWait();
        if (option.get().equals(ButtonType.OK)){
        return true;
        }else{
        return false;
        }
    }

}