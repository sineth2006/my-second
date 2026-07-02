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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project_ii.bo.AttendanceMarkingBo;
import lk.ijse.project_ii.bo.impl.AttendanceMarkingBoImpl;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.AttendanceMarkingDTO;
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
   private AlertMessege alert=new AlertMessege();
    private final AttendanceMarkingBo attendanceMarkingBo = new AttendanceMarkingBoImpl();
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

    ObservableList<AttendanceMarkingEntity> items =
            attendance_marking_table.getItems();

    if (items == null || items.isEmpty()) {
        alert.errorMessage("Please load and mark attendance first!");
        return;
    }

    try {

        // Convert ENTITY → DTO (because BO expects DTO)
        List<AttendanceMarkingDTO> dtoList = new ArrayList<>();

        for (AttendanceMarkingEntity entity : items) {

            AttendanceMarkingDTO dto = new AttendanceMarkingDTO(
                    entity.getAttendanceId(),
                    entity.getSchedulingId(),
                    entity.getStudentId(),
                    entity.getStatus().name()   // present / absent
            );

            dtoList.add(dto);
        }

        boolean isSaved = attendanceMarkingBo.saveAttendance(dtoList);

        if (isSaved) {
            alert.successmessage("Attendance Saved Successfully!");

            // optional: refresh table or keep data
            attendance_marking_table.refresh();

            clearAttendance(); // optional method
        } else {
            alert.errorMessage("Failed to save attendance!");
        }

    } catch (Exception e) {
        e.printStackTrace();
        alert.errorMessage("Error: " + e.getMessage());
    
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
            // Retrieve data through layers
            List<Integer> scheduleIds = attendanceMarkingBo.loadAvailableSchedules();
            if (scheduleIds != null) {
                class_combobox.getItems().addAll(scheduleIds);
            }
        } catch (Exception e) {
            // FIX: Print the stack trace so database exceptions (like closed connections) show up in your console
            e.printStackTrace();
        }
    }
    
    @FXML
    void loadTable() throws SQLException {

    Integer selectedSchedule = class_combobox.getValue();

    if (selectedSchedule == null) {
        attendance_marking_table.getItems().clear();
        return;
    }

    ArrayList<AttendanceMarkingDTO> dtoList =
            (ArrayList<AttendanceMarkingDTO>) attendanceMarkingBo
                    .generateAttendanceSheet(selectedSchedule);

    ObservableList<AttendanceMarkingEntity> list =
            FXCollections.observableArrayList();

for (AttendanceMarkingDTO dto : dtoList) {

    AttendanceMarkingEntity.AttendanceStatus status;

    if (dto.getStatus().equalsIgnoreCase("present")) {
        status = AttendanceMarkingEntity.AttendanceStatus.present;
    } else {
        status = AttendanceMarkingEntity.AttendanceStatus.absent;
    }

    AttendanceMarkingEntity entity = new AttendanceMarkingEntity(
            dto.getAttendanceId(),
            dto.getSchedulingId(),
            dto.getStudentId(),
            status
    );

    list.add(entity);
    }

    attendance_marking_table.setItems(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // FIXED COLUMN MAPPING (IMPORTANT)
        attendance_id_column.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        student_id_column.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        scheduling_id_column.setCellValueFactory(new PropertyValueFactory<>("schedulingId"));

        // STATUS COLUMN WITH CHECKBOX
        status_column.setCellValueFactory(param -> {
            AttendanceMarkingEntity entity = param.getValue();
            CheckBox checkBox = new CheckBox();

            checkBox.setSelected(entity.getStatus() == AttendanceMarkingEntity.AttendanceStatus.present);

            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                entity.setStatus(newVal
                        ? AttendanceMarkingEntity.AttendanceStatus.present
                        : AttendanceMarkingEntity.AttendanceStatus.absent);
            });

            return new javafx.beans.property.SimpleObjectProperty<>(checkBox);
        });

        load_combobox1_table();
    }
    
    @FXML
    private void clearAttendance() {
    class_combobox.getSelectionModel().clearSelection();
    attendance_marking_table.getItems().clear();
}
}
