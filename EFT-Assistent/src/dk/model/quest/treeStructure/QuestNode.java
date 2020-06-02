package dk.model.quest.treeStructure;

import dk.tools.visualizer.VisualizerQuestNode;
import dk.model.quest.Quest;

import java.util.ArrayList;

public class QuestNode {
    private ArrayList<QuestNode> requiredQuests = new ArrayList<>();
    private ArrayList<QuestNode> followingQuests = new ArrayList<>();
    private Quest quest;

    public QuestNode(Quest quest) {
        this.quest = quest;
    }

    public void AddRequiredQuestNode(QuestNode node){
        requiredQuests.add(node);
    }

    //public void reCheckSta

    public void addSelfToRequiredQuests(){
        for(QuestNode node : requiredQuests){
            node.addFollowingQuestNode(this);
        }
    }

    public void addFollowingQuestNode(QuestNode node){
        followingQuests.add(node);
    }

    public int getQuestId(){
        return quest.getId();
    }

    public void completeQuestAndRequiredQuests(){
        for(QuestNode reqQuest : requiredQuests){
            reqQuest.completeQuestAndRequiredQuests();
        }

        quest.complete();
    }

    public ArrayList<QuestNode> getRootNodes(){
        ArrayList<QuestNode> previousNodes = new ArrayList<>();
        for(QuestNode reQuest : requiredQuests){
            previousNodes.addAll(reQuest.getRootNodes());
        }

        if(requiredQuests.size() == 0){
            previousNodes.add(this);
        }

        return previousNodes;
    }

    public Quest getQuest(){
        return quest;
    }

    public ArrayList<VisualizerQuestNode> getVisualizerNodes(int currentLayer){
        ArrayList<VisualizerQuestNode> visualNodes = new ArrayList<>();
        for(QuestNode node : followingQuests){
            visualNodes.addAll(node.getVisualizerNodes(currentLayer + 1));
        }
        visualNodes.add(new VisualizerQuestNode(currentLayer, quest, followingQuests));

        return visualNodes;
    }

    public ArrayList<QuestNode> getFollowingQuests(){
        return followingQuests;
    }
}
