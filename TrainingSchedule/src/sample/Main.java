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

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent obj = loader.load();
        Controller ctrl = loader.getController();

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
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
