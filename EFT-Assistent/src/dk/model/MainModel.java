package dk.model;

import dk.model.quest.QuestManager;

public class MainModel {

    private QuestManager qm;

    private PlayerInfo playerInfo;

    public MainModel() {
        playerInfo = new PlayerInfo(0);
        qm = new QuestManager();
        qm.loadQuests(playerInfo);
    }

    public QuestManager getQm() {
        return qm;
    }
}
