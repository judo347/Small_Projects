package dk.model.quest.treeStructure;

import dk.model.PlayerInfo;
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

    /** Check if this quest can be active. If so, the following quests is also called. */
    public void initialQuestStateActiveCheck(PlayerInfo playerInfo){
        boolean canBeActive = quest.setStateActive(playerInfo);
        if(canBeActive){
            for(QuestNode questNode : followingQuests){
                questNode.initialQuestStateActiveCheck(playerInfo);
            }
        }
    }

    /** */
    public void reEvalLockedQuests(PlayerInfo playerInfo){
        if(quest.getState() == Quest.QuestState.LOCKED){
            initialQuestStateActiveCheck(playerInfo);
            return;
        }else{
            for(QuestNode questNode : followingQuests){
                questNode.reEvalLockedQuests(playerInfo);
            }
        }
    }

    public void completeQuest(PlayerInfo playerInfo){
        if(quest.getState() == Quest.QuestState.COMPLETED){
            throw new IllegalArgumentException("This action is not possible!");
        }

        if(quest.getState() == Quest.QuestState.LOCKED){
            throw new IllegalArgumentException("This action is not possible!");
        }

        quest.setState(Quest.QuestState.COMPLETED);
        for(QuestNode questNode : followingQuests){
            questNode.initialQuestStateActiveCheck(playerInfo); //TODO rename method?
        }
    }

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

    public ArrayList<QuestNode> getAllRequiredQuests(){
        ArrayList<QuestNode> requiredQuestsCopy = new ArrayList<>(requiredQuests);
        for(QuestNode reqQuestNode : requiredQuests){
            requiredQuestsCopy.addAll(reqQuestNode.getAllRequiredQuests());
        }

        return requiredQuestsCopy;
    }
}
