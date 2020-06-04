package dk.tools.visualizer;

import dk.model.PlayerInfo;
import dk.model.quest.Quest;
import dk.model.quest.QuestManagerTree;
import dk.model.quest.treeStructure.QuestModel;
import dk.model.quest.treeStructure.QuestNode;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestStructureVisualizer {

    private static int questBoxWidth = 160;
    private static int questBoxHeight = 40;
    private static int questBoxSpacing = 50;

    public QuestStructureVisualizer(Group root, QuestManagerTree qmt) {
        populate(root, qmt);
    }

    public void populate(Group root, QuestManagerTree qmt){

        ArrayList<QuestNode> rootNodes = qmt.getQuestModel().getRootNodes();
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
                    drawRectableAt(root, x, y, vqn.quest.getQuestName(), vqn.quest.getState());
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

    private void drawRectableAt(Group root, int x, int y, String labelText, Quest.QuestState questState){
        Color colorOfBox;
        if(questState == Quest.QuestState.ACTIVE){
            colorOfBox = Color.YELLOW;
        } else if(questState == Quest.QuestState.COMPLETED){
            colorOfBox = Color.GREEN;
        } else {
            colorOfBox = Color.RED;
        }

        //Filled rectangle
        Rectangle rect1 = new Rectangle(x, y, questBoxWidth, questBoxHeight);
        Text text = new Text(x + 10, y + 20, labelText);
        rect1.setFill(colorOfBox);
        root.getChildren().add(rect1);
        root.getChildren().add(text);
    }

    private void drawLine(Group root, int fromX, int fromY, int toX, int toY){
        Line line = new Line(fromX + questBoxWidth, fromY + questBoxHeight/2, toX, toY + questBoxHeight/2);
        line.setStyle("-fx-stroke: red; -fx-stroke-width: 2");
        root.getChildren().add(line);
    }
}
