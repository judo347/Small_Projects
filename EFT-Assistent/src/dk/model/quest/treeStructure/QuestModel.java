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

    public void setQuestStatesFromCompletedQuestIds(PlayerInfo playerInfo, ArrayList<Integer> completedQuestIdsFromSave){
        while(!completedQuestIdsFromSave.isEmpty()){
            QuestNode questNodeTop = questNodeMap.get(questIdMap.get(completedQuestIdsFromSave.get(0)));
            ArrayList<Integer> completedQuestsIds = questNodeTop.completeThisAndAllPriors(playerInfo);
            //System.out.println("Completed quests which ids should be removed: " + completedQuestsIds);
            for(Integer completedId : completedQuestsIds){
                System.out.println("Searching for completed id.");
                boolean wasRemoved = false;
                for(Integer comIdsSave : completedQuestIdsFromSave){
                    if(completedId.compareTo(comIdsSave) == 0){
                        if(!completedQuestIdsFromSave.remove(comIdsSave)){
                            throw new IllegalArgumentException("Quest id was not found, and not removed!");
                        }
                        wasRemoved = true;
                        System.out.println("Completed quest id was found and removed: " + completedId);
                        StringBuilder remainingIds = new StringBuilder("Remaining ids: ");
                        for(Integer remainingId : completedQuestIdsFromSave){
                            remainingIds.append(remainingId).append(" ");
                        }
                        System.out.println(remainingIds.toString());
                        break;
                    }
                }

                if(!wasRemoved){
                    throw new IllegalArgumentException("This should not be possible!! Removing a quest from the list of ids which were used to find that quest.");
                }
            }
        }

        //recheck for quests that should be active
        recheckQuestRequirements(playerInfo);
    }

    public ArrayList<Quest> getQuestsWithState(Quest.QuestState state){
        ArrayList<Quest> foundQuests = new ArrayList<>();
        for(Quest quest : questIdMap.values()){
            if(quest.getState() == state){
                foundQuests.add(quest);
            }
        }

        return foundQuests;
    }

    public int getTotalNumberOfQuests(){
        return questIdMap.size();
    }
}
