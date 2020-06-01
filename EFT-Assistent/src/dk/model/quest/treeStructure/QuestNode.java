package dk.model.quest.treeStructure;

import dk.model.quest.Quest;

import java.util.ArrayList;

public class QuestNode {
    private ArrayList<QuestNode> requiredQuests;
    private Quest quest;

    public QuestNode(Quest quest) {
        this.quest = quest;
    }

    public void AddRequiredQuestNode(QuestNode node){
        requiredQuests.add(node);
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

        return previousNodes;
    }

    public Quest getQuest(){
        return quest;
    }
}
