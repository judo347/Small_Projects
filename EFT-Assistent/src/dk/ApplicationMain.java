package dk;

import dk.model.MapType;
import dk.model.Quest;
import dk.view.PrimarySceneController;
import dk.view.QuestCardController;
import dk.view.QuestInstantiator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationMain extends Application {

    private static String appWindowName = "EFT: Quest Assistant";

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dk/view/PrimaryScene.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dk/view/PrimaryScene.fxml"));
            Pane root = fxmlLoader.load();
            PrimarySceneController rootController = fxmlLoader.getController();

            fillScene(rootController);

            primaryStage.setTitle(appWindowName);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }catch (Exception e){

        }
    }

    private void fillScene(PrimarySceneController rootController){

        QuestInstantiator QI = new QuestInstantiator();
        //Quest quest = QI.getQuestTemp1();
        for(Quest quest : QI.allQuests){
            Pane questCard = createQuestCard(quest);

            MapType mapType;

            if(quest.getMaps().size() == 1)
                mapType = quest.getMaps().get(0);
            else
                mapType = MapType.MIXED;

            rootController.addQuestCard(questCard, mapType);
        }
    }

    private Pane createQuestCard(Quest quest){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dk/view/QuestCard.fxml"));
            Pane questCard = fxmlLoader.load();
            QuestCardController questCardController = (QuestCardController)fxmlLoader.getController();

            questCardController.setValues(quest);

            return questCard;

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }
}
