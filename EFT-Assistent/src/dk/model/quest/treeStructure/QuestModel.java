package dk.model.quest.treeStructure;

import dk.model.quest.Quest;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestModel {

    private ArrayList<QuestNode> rootNodes = new ArrayList<>();
    private HashMap<Integer, Quest> questIdMap = new HashMap<>();
    private HashMap<Quest, QuestNode> questNodeMap = new HashMap<>();

    public QuestModel(ArrayList<Quest> allQuests){

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
        HashMap<Integer, Quest> foundRootNodes = new HashMap<>();
        for(Quest quest : allQuests){
            ArrayList<QuestNode> rootNodes = questNodeMap.get(quest).getRootNodes();
            for(QuestNode rootNode : rootNodes){
                foundRootNodes.put(rootNode.getQuestId(), rootNode.getQuest());
            }
        }
    }

    public void colleteQuestAndPareQuestsRecursively(Quest questToComplete){
        questNodeMap.get(questToComplete).completeQuestAndRequiredQuests();
    }
}
