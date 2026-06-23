package lk.ijse.project_ii.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
  public static String currentUserRole = ""; 
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LogingUI"), 320, 500);
        
        stage.setMinWidth(330);
        stage.setMinHeight(550);
        stage.setTitle("Student Attendance Management System");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        //replace
      FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
}

    public static void main(String[] args) {
        launch();
    }

}