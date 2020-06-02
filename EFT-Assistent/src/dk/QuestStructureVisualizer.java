package dk;

import dk.model.PlayerInfo;
import dk.model.quest.QuestManagerTree;
import dk.model.quest.treeStructure.QuestModel;
import dk.model.quest.treeStructure.QuestNode;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class QuestStructureVisualizer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);

        //drawRectableAt(root, 10,10);
        test(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void test(Group root){
        QuestManagerTree qmt = new QuestManagerTree(new PlayerInfo());
        QuestModel questModel = qmt.getQuestModel();
        System.out.println("QMT and QM has been instantiated.");
        ArrayList<QuestNode> rootNodes = questModel.getRootNodes();
        ArrayList<VisualizerQuestNode> visualizerQuestNodes = new ArrayList<>();
        for(QuestNode node : rootNodes){
            visualizerQuestNodes.addAll(node.getVisualizerNodes(0));
        }

        int i = 0;
        while(!visualizerQuestNodes.isEmpty()){
            ArrayList<VisualizerQuestNode> drawnNodes = new ArrayList<>();
            int y = 10;
            for(VisualizerQuestNode vqn : visualizerQuestNodes){
                if(vqn.layer == i){
                    int x = (i * (160 + 20) + 20);
                    System.out.println("Drawing node at: x:" + x + " y:" + y);
                    drawRectableAt(root, x, y, vqn.quest.getQuestName());
                    drawnNodes.add(vqn);
                    y += 50;
                }
            }
            visualizerQuestNodes.removeAll(drawnNodes);
            System.out.println("Visualizer nodes left: " + visualizerQuestNodes.size());;
            i++;
        }
    }

    public static void drawRectableAt(Group root, int x, int y, String labelText){
        //Filled rectangle
        Rectangle rect1 = new Rectangle(x, y, 160, 40);
        Text text = new Text(x + 10, y + 20, labelText);
        rect1.setFill(Color.BLUE);
        root.getChildren().add(rect1);
        root.getChildren().add(text);
    }
}
