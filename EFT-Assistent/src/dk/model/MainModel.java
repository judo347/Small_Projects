package dk.model;

import dk.data.JSONParserHelper;
import dk.model.quest.QuestManager;
import dk.view.PrimarySceneController;

import java.util.ArrayList;

public class MainModel {

    private final PrimarySceneController psc;

    private QuestManager qm;
    private PlayerInfo playerInfo;

    public MainModel(PrimarySceneController psc) {
        this.psc = psc;
        playerInfo = new PlayerInfo(0);
        qm = new QuestManager();
        qm.loadQuests(playerInfo);
    }

    public void incrementTraderLoyaltyLevel(TraderType traderType){
        playerInfo.incrementLoyaltyLevel(traderType);
        qm.doPrerequisiteQuestCheckForLocked();
    }

    public void incrementPlayerLevel(){
        playerInfo.incrementPlayerLevel();
        qm.doPrerequisiteQuestCheckForLocked();
    }

    public void loadSlot(int slotNumber){
        JSONParserHelper jph = new JSONParserHelper();
        SaveData saveData = jph.loadSlot(slotNumber);

        playerInfo.reload(saveData.playerInfo);
        qm.reloadFromCompletedQuests(saveData.completedQuestIds, saveData.playerInfo);
        psc.reloadPlayerInfoVisuals();
        psc.reloadQuestVisuals();
    }

    public void saveSlot(int slotNumber){
        JSONParserHelper jph = new JSONParserHelper();
        boolean didSave = jph.SaveData(slotNumber, new ArrayList<>(qm.getCompleted()), playerInfo);
        if(didSave)
            System.out.println("Save successful!");
        else
            System.out.println("Save failed!");
    }

    public QuestManager getQm() {
        return qm;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
