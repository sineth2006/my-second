module lk.ijse.project_ii {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql; // Crucial for your MySQL connector dependency

    // Add access to your main package where App.java lives:
    exports lk.ijse.project_ii.main;
    opens lk.ijse.project_ii.main to javafx.fxml;

    // Keep your existing controllers and entities open/exported:
    exports lk.ijse.project_ii.viewController;
    opens lk.ijse.project_ii.viewController to javafx.fxml;
    opens lk.ijse.project_ii.entity to javafx.base;
}
