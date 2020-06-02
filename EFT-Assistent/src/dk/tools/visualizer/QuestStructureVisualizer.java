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

public class QuestStructureVisualizer extends Application {

    public static int questBoxWidth = 160;
    public static int questBoxHeight = 40;
    public static int questBoxSpacing = 50;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        Scene scene = new Scene(scrollPane, 500, 500, Color.BLACK);

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

        HashMap<Integer, int[]> idToDrawnCoordinate = new HashMap<>();
        int i = 0;
        ArrayList<VisualizerQuestNode> visualizerQuestNodesCopy = new ArrayList<>(visualizerQuestNodes);
        while(!visualizerQuestNodes.isEmpty()){
            ArrayList<VisualizerQuestNode> drawnNodes = new ArrayList<>();
            int y = 10;
            for(VisualizerQuestNode vqn : visualizerQuestNodes){
                if(vqn.layer == i){
                    int x = (i * (questBoxWidth + questBoxSpacing) + 20);
                    System.out.println("Drawing node at: x:" + x + " y:" + y);
                    drawRectableAt(root, x, y, vqn.quest.getQuestName());
                    drawnNodes.add(vqn);
                    idToDrawnCoordinate.put(vqn.quest.getId(), new int[]{x, y});
                    y += 50;
                }
            }
            visualizerQuestNodes.removeAll(drawnNodes);
            System.out.println("Visualizer nodes left: " + visualizerQuestNodes.size());;
            i++;
        }

        //Draw lines
        for(VisualizerQuestNode vqn : visualizerQuestNodesCopy){
            int[] fromCoords = idToDrawnCoordinate.get(vqn.quest.getId());
            for(QuestNode followingQuest: vqn.followingQuests){
                int[] toCoords = idToDrawnCoordinate.get(followingQuest.getQuestId());
                drawLine(root, fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);
            }
        }
    }

    public static void drawRectableAt(Group root, int x, int y, String labelText){
        //Filled rectangle
        Rectangle rect1 = new Rectangle(x, y, questBoxWidth, questBoxHeight);
        Text text = new Text(x + 10, y + 20, labelText);
        rect1.setFill(Color.BLUE);
        root.getChildren().add(rect1);
        root.getChildren().add(text);
    }

    public static void drawLine(Group root, int fromX, int fromY, int toX, int toY){
        Line line = new Line(fromX + questBoxWidth, fromY + questBoxHeight/2, toX, toY + questBoxHeight/2);
        line.setStyle("-fx-stroke: red; -fx-stroke-width: 2");
        root.getChildren().add(line);
    }
}
