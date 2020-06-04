package dk.tools.visualizer;

import dk.model.PlayerInfo;
import dk.model.quest.QuestManagerTree;
import dk.model.quest.treeStructure.QuestModel;
import dk.model.quest.treeStructure.QuestNode;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestStructureVisualizerMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        Scene scene = new Scene(scrollPane, 500, 500, Color.BLACK);
        QuestManagerTree qmt = new QuestManagerTree(new PlayerInfo());
        QuestStructureVisualizer qsv = new QuestStructureVisualizer(root, qmt);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
