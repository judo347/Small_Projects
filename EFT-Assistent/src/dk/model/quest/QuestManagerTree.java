package dk.model.quest;

import dk.data.JSONParserHelper;
import dk.model.PlayerInfo;
import dk.model.quest.treeStructure.QuestModel;

import java.util.ArrayList;

public class QuestManagerTree {

    private QuestModel questModel;

    public QuestManagerTree(PlayerInfo playerInfo) {

        ArrayList<Quest> allQuests = loadAllQuests();

        questModel = new QuestModel(allQuests, playerInfo);
    }

    /** Loads all quests from data file. */
    private ArrayList<Quest> loadAllQuests(){
        JSONParserHelper jph = new JSONParserHelper();
        return jph.getAllQuestsFromFile();
    }

    public void playerInfoHasBeenUpdated(PlayerInfo playerInfo){
        questModel.recheckQuestRequirements(playerInfo);
    }

    public void completeQuest(Quest quest, PlayerInfo playerInfo){
        questModel.completeQuest(quest, playerInfo);
    }

    /** Reloads quest model based on the given completed quest ids. (Sets active and completed)
     Used when loading saved data. */
    public void reloadFromCompletedQuests(PlayerInfo playerInfo, ArrayList<Integer> completedQuestIds){
        questModel.setQuestStatesFromCompletedQuestIds(playerInfo, new ArrayList<>(completedQuestIds));
    }

    public ArrayList<Quest> getCompletedQuests(){
        return questModel.getQuestsWithState(Quest.QuestState.COMPLETED);
    }

    public ArrayList<Quest> getActiveQuests(){
        return questModel.getQuestsWithState(Quest.QuestState.ACTIVE);
    }

    public ArrayList<Quest> getLockedQuests(){
        return questModel.getQuestsWithState(Quest.QuestState.LOCKED);
    }

    public int getTotalNumberOfQuests(){
        return questModel.getTotalNumberOfQuests();
    }

    public QuestModel getQuestModel(){
        return questModel;
    }
}
