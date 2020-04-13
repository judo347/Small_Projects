package dk;

import dk.model.MainModel;
import dk.view.PrimarySceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

public class ApplicationMain extends Application {

    private static String appWindowName = "EFT: Quest Assistant";
    private MainModel mainModel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {


            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dk/view/PrimaryScene.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dk/view/PrimaryScene.fxml"));
            Pane root = fxmlLoader.load();
            PrimarySceneController rootController = fxmlLoader.getController();
            mainModel = new MainModel(rootController);
            rootController.setModelAndStage(mainModel, primaryStage);

            //addQuestsToScene(rootController);
            //rootController.reloadQuestVisuals();
            mainModel.loadSlot(0);

            //primaryStage.setResizable(false);

            primaryStage.setTitle(appWindowName);
            primaryStage.setScene(new Scene(root));
            root.resize(600,450);
            primaryStage.show();

        }catch (Exception e){

        }
    }

    @Override
    public void stop(){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Save progression?");
        confirmation.setHeaderText("Press \"ok\", to save your progression.");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.get() == ButtonType.OK){
            mainModel.saveSlot(0);
        }
    }
}
