package dk.tools.visualizer;

import dk.model.quest.Quest;
import dk.model.quest.treeStructure.QuestNode;

import java.util.ArrayList;

public class VisualizerQuestNode {

    public int x;
    public int y;
    public int layer;
    public Quest quest;
    public ArrayList<QuestNode> followingQuests = new ArrayList<>();

    public VisualizerQuestNode(int layer, Quest quest, ArrayList<QuestNode> followingQuests) {
        this.layer = layer;
        this.quest = quest;
        this.followingQuests = followingQuests;
    }
}
