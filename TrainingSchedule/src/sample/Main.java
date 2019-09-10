package sample;

import files.FileManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Model;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("sample.fxml"));
        Parent obj = (Parent)loader.load();
        final Controller ctrl = (Controller)loader.getController();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                FileManager.saveData(ctrl.getModel());
            }
        });
        Model loadedModel = FileManager.loadData();
        ctrl.initialize(loadedModel);
        primaryStage.setTitle("Training Schedule");
        primaryStage.setScene(new Scene(obj));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}