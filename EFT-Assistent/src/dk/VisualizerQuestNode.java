package dk;

import dk.model.quest.Quest;

public class VisualizerQuestNode {

    public int x;
    public int y;
    public int layer;
    public Quest quest;

    public VisualizerQuestNode(int layer, Quest quest) {
        this.layer = layer;
        this.quest = quest;
    }
}
