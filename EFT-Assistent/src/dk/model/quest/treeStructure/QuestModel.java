package dk.model.quest.treeStructure;

import dk.model.PlayerInfo;
import dk.model.quest.Quest;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestModel {

    private ArrayList<QuestNode> rootNodes = new ArrayList<>();
    private HashMap<Integer, Quest> questIdMap = new HashMap<>();
    private HashMap<Quest, QuestNode> questNodeMap = new HashMap<>();

    public QuestModel(ArrayList<Quest> allQuests, PlayerInfo playerInfo){

        //Create a node for each quest and store in map
        for(Quest quest : allQuests){
            QuestNode questNode = new QuestNode(quest);
            questNodeMap.put(quest, questNode);
            questIdMap.put(quest.getId(), quest);
        }

        //Run through each node and add required quests
        for(Quest quest : allQuests){
            QuestNode questNode = questNodeMap.get(quest);
            for(Integer reqId: quest.getRequiredQuestIds()){
                questNode.AddRequiredQuestNode(questNodeMap.get(questIdMap.get(reqId)));
            }
        }

        //Find root notes //TODO: expensive approach
        HashMap<Integer, QuestNode> foundRootNodes = new HashMap<>();
        for(Quest quest : allQuests){
            ArrayList<QuestNode> rootNodes = questNodeMap.get(quest).getRootNodes();
            for(QuestNode rootNode : rootNodes){
                foundRootNodes.put(rootNode.getQuestId(), rootNode);
            }
        }

        rootNodes.addAll(foundRootNodes.values());

        //Give notes their following notes
        for(Quest quest : allQuests){
            QuestNode questNode = questNodeMap.get(quest);
                questNode.addSelfToRequiredQuests();
        }

        initialQuestStateCheck(playerInfo);
    }

    //Checks for which quests should be active at launch
    private void initialQuestStateCheck(PlayerInfo playerInfo) {
        for(QuestNode questNode : rootNodes){
            questNode.initialQuestStateActiveCheck(playerInfo);
        }
    }

    /** Searches for locked quests that can be active.*/
    public void recheckQuestRequirements(PlayerInfo playerInfo){
        for(QuestNode questNode : rootNodes){
            questNode.reEvalLockedQuests(playerInfo);
        }
    }

    public void completeQuest(Quest quest, PlayerInfo playerInfo){
        questNodeMap.get(quest).completeQuest(playerInfo);
    }

    public void colleteQuestAndPreQuestsRecursively(Quest questToComplete){
        questNodeMap.get(questToComplete).completeQuestAndRequiredQuests();
    }

    public ArrayList<QuestNode> getRootNodes(){
        return rootNodes;
    }

    public void setQuestStatesFromCompletedQuestIds(PlayerInfo playerInfo, ArrayList<Integer> completedQuestIds){

        while(!completedQuestIds.isEmpty()){
            QuestNode questNodeTop = questNodeMap.get(questIdMap.get(completedQuestIds.get(0)));
            ArrayList<QuestNode> nodesToRoot = questNodeTop.getAllRequiredQuests();
            nodesToRoot.add(questNodeTop);
            //remove all from completedQuestIds, if one is not found = corrupt save
            for(QuestNode questNodeToRoot : nodesToRoot){
                //Remove
                boolean wasFoundAndRemove = false;
                for(Integer completedQuestId : new ArrayList<>(completedQuestIds)){
                    if(completedQuestId.compareTo(questNodeToRoot.getQuestId()) == 0){ //TODO is 0 correct?
                        wasFoundAndRemove = true;
                        completedQuestIds.remove(completedQuestId);
                        break;
                    }
                }
                if(!wasFoundAndRemove){
                    throw new IllegalArgumentException("This should not be possible!! Removing a quest from the list of ids which were used to find that quest.");
                }

                //Complete
                boolean canBeActive = questNodeToRoot.getQuest().setStateActive(playerInfo);
                if(!canBeActive){
                    throw new IllegalArgumentException("Corrupt save! PlayerInfo requirements not met for completed quest with id: " + questNodeToRoot.getQuestId());
                }
            }
        }
    }

    public ArrayList<Quest> getCompletedQuests(){
        ArrayList<Quest> completedQuests = new ArrayList<>();
        for(Quest quest : questIdMap.values()){
            if(quest.getState() == Quest.QuestState.COMPLETED){
                completedQuests.add(quest);
            }
        }

        return completedQuests;
    }
}
