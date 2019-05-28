package sample;

import files.FileManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent obj = loader.load();
        Controller ctrl = loader.getController();

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Model loadedModel = FileManager.loadData();
        ctrl.initialize(loadedModel);

        primaryStage.setTitle("Training Schedule");
        primaryStage.setScene(new Scene(obj, 800, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
