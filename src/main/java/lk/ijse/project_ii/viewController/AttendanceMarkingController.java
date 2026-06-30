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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.entity.AttendanceMarkingEntity;
import lk.ijse.project_ii.entity.AttendanceMarkingEntity.AttendanceStatus;

/**
 * FXML Controller class
 *
 * @author Sineth
 */
public class AttendanceMarkingController implements Initializable {
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private AlertMessege alert = new AlertMessege();
    
    @FXML
    private Button attendance_marking_back_button;

    @FXML
    private Button attendance_marking_clean_button;

    @FXML
    private Button attendance_marking_save_attendance_button;

    @FXML
    private ComboBox<Integer> class_combobox;
    
    @FXML
    private TableColumn<AttendanceMarkingEntity, Integer> student_id_column;

    @FXML
    private TableView<AttendanceMarkingEntity> attendance_marking_table;

    @FXML
    private TableColumn<AttendanceMarkingEntity, Integer> scheduling_id_column;

    @FXML
    private TableColumn<AttendanceMarkingEntity, javafx.scene.control.CheckBox> status_column;

    @FXML
    private TableColumn<AttendanceMarkingEntity, Integer> attendance_id_column;

    @FXML
    void attendance_marking_back_button_OnAction(ActionEvent event) throws IOException {       
        String fxmlPath;
        String title;
        
        if ("Lecturer".equals(App.currentUserRole)) {
            fxmlPath = "LectureDashboad.fxml";
            title = "Lecture Dashboad";
        } else {
            fxmlPath = "AdminDashboad.fxml";
            title = "Admin Dashboad";
        }
        Parent root = FXMLLoader.load(App.class.getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage = (Stage) attendance_marking_back_button.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void attendance_marking_clean_button_OnAction(ActionEvent event) {
        class_combobox.getSelectionModel().clearSelection();
        class_combobox.setValue(null);
        attendance_marking_table.getItems().clear();
    }

    @FXML
    void attendance_marking_save_attendance_button_OnAction(ActionEvent event) throws SQLException {
       
          ObservableList<AttendanceMarkingEntity> items = attendance_marking_table.getItems();
    if (items.isEmpty()) {
        return;
    }

    try {
        connect = DBConnection.getInstance().getConnection();
        
        // FIX: Omit attendance_id entirely from both segments of the SQL string template
        String sql = "INSERT INTO student_attendance (scheduling_id, student_id, status) VALUES (?, ?, ?)";
        PreparedStatement pst = connect.prepareStatement(sql);
        
        for (AttendanceMarkingEntity row : items) {
            // Parameter index 1 maps to scheduling_id
            pst.setInt(1, row.getSchedulingId());
            
            // Parameter index 2 maps to student_id
            pst.setInt(2, row.getStudentId());
            
            // Parameter index 3 maps to status
            if (row.getStatus() == AttendanceStatus.present) {
                pst.setString(3, "Present");
            } else {
                pst.setString(3, "Absent");
            }
            
            pst.addBatch(); 
        }  
        
        pst.executeBatch();
        alert.successmessage("Attendance Saved Successfully");
        System.out.println("Attendance saved successfully!");
        
    } catch (Exception e) {
        e.printStackTrace();
        alert.errorMessage("Database Saving Error: " + e.getMessage());
    }
    }
    
    @FXML
    void attendance_marking_table_clicked(MouseEvent event) {

    }
    
    @FXML
    void class_comboboxOnAction(ActionEvent event) throws SQLException {
        loadTable();
    }
    
    @FXML
    void load_combobox1_table() {
        class_combobox.getItems().clear();
        try {
            connect = DBConnection.getInstance().getConnection();
            String sql = "SELECT scheduling_id FROM class_scheduling";
            PreparedStatement pst = connect.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Integer value = rs.getInt("scheduling_id");
                class_combobox.getItems().add(value);
                System.out.println("ok...ok...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void loadTable() {
        Integer selectedSchedule = class_combobox.getValue();
        System.out.println("Selected schedule = " + selectedSchedule);

        if (selectedSchedule == null) return;
        
        ObservableList<AttendanceMarkingEntity> attendancemarkinglist = FXCollections.observableArrayList();
        
        try {
            connect = DBConnection.getInstance().getConnection();

            String sql =  "SELECT sm.student_id, sm.student_name "
                   + "FROM student_management sm "
                   + "JOIN class_scheduling cs ON sm.course_id = cs.course_id "
                   + "WHERE cs.scheduling_id = ?";
                       
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, selectedSchedule); 
            
            result = prepare.executeQuery();
            int count = 0;

            while (result.next()) {
                count++;
                  int studentId = result.getInt("student_id");
            String studentName = result.getString("student_name");
                
                // Print check data directly to console log output
                System.out.println(" " + studentId + " " + result.getString("student_name"));
                
                // Maps default fallback status value using your nested entity enum
                AttendanceStatus defaultStatus = AttendanceStatus.absent;

                // FIX: Adding item instances dynamically into your observable array list
                attendancemarkinglist.add(new AttendanceMarkingEntity(
                     count,        // Maps to attendance_id field
                    selectedSchedule,  // Maps to scheduling_id field
                    studentId,         // Maps to student_id field
                    defaultStatus      // Maps to status field enum
                ));
            }

            System.out.println("TOTAL ROWS IN TABLE: " + count);
            
            // FIX: Bind the generated list to populate the visible UI grid rows
            attendance_marking_table.setItems(attendancemarkinglist);
            attendance_marking_table.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind column layout grids to look up variable keys inside AttendanceMarkingEntity
        scheduling_id_column.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
    attendance_id_column.setCellValueFactory(new PropertyValueFactory<>("studentId")); 
    student_id_column.setCellValueFactory(new PropertyValueFactory<>("schedulingId"));
    status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
    
      status_column.setCellValueFactory(param -> {
        AttendanceMarkingEntity entity = param.getValue();
        CheckBox checkBox = new CheckBox();

        // Check the box if current status is present
        checkBox.setSelected(entity.getStatus() == AttendanceStatus.present);

        // Update the underlying data model instantly whenever a user clicks the CheckBox
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                entity.setStatus(AttendanceStatus.present);
            } else {
                entity.setStatus(AttendanceStatus.absent);
            }
        });

        return new javafx.beans.property.SimpleObjectProperty<>(checkBox);
    });

        load_combobox1_table();
    }
}
