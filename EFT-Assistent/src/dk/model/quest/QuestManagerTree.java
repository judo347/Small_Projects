package dk.model.quest;

import dk.data.JSONParserHelper;
import dk.model.PlayerInfo;
import dk.model.quest.treeStructure.QuestModel;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestManagerTree {

    private QuestModel questModel;
    private PlayerInfo playerInfo;

    public QuestManagerTree(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;

        ArrayList<Quest> allQuests = loadAllQuests();

        questModel = new QuestModel(allQuests);
    }

    /** Loads all quests from data file. */
    private ArrayList<Quest> loadAllQuests(){
        JSONParserHelper jph = new JSONParserHelper();
        return jph.getAllQuestsFromFile();
    }

    public QuestModel getQuestModel(){
        return questModel;
    }
}
