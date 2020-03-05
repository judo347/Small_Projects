package dk;

import dk.model.MainModel;
import dk.model.MapType;
import dk.model.quest.Quest;
import dk.view.PaneAndController;
import dk.view.PrimarySceneController;
import dk.view.QuestCardController;
import dk.view.QuestInstantiator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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
            rootController.setMainModel(mainModel);

            //addQuestsToScene(rootController);
            rootController.reloadQuestVisuals();

            //primaryStage.setResizable(false);

            primaryStage.setTitle(appWindowName);
            primaryStage.setScene(new Scene(root));
            root.resize(250,250);
            primaryStage.show();

        }catch (Exception e){

        }
    }
}
