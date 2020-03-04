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
            mainModel = new MainModel();

            //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dk/view/PrimaryScene.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dk/view/PrimaryScene.fxml"));
            Pane root = fxmlLoader.load();
            PrimarySceneController rootController = fxmlLoader.getController();

            fillScene(rootController);

            //primaryStage.setResizable(false);

            primaryStage.setTitle(appWindowName);
            primaryStage.setScene(new Scene(root));
            root.resize(250,250);
            primaryStage.show();

        }catch (Exception e){

        }
    }

    private void fillScene(PrimarySceneController rootController){

        for(Quest quest : mainModel.getQm().getActiveQuests()){
            PaneAndController questCardAndController = createQuestCard(quest, rootController);

            MapType mapType;

            if(quest.getMaps().size() == 1)
                mapType = quest.getMaps().get(0);
            else
                mapType = MapType.MIXED;

            rootController.addQuestCard(questCardAndController, mapType);
        }
    }

    private PaneAndController createQuestCard(Quest quest, PrimarySceneController psc){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dk/view/QuestCard.fxml"));
            Pane questCard = fxmlLoader.load();
            QuestCardController questCardController = (QuestCardController)fxmlLoader.getController();

            questCardController.setValues(quest);
            questCardController.setParent(psc);

            return new PaneAndController(questCard, questCardController);

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }
}
