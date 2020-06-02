package dk.model;

import dk.Main;
import dk.data.JSONParserHelper;
import dk.model.quest.QuestManager;
import dk.model.quest.QuestManagerTree;
import dk.view.PrimarySceneController;

import java.util.ArrayList;

public class MainModel {

    private boolean runningHeadless;

    private PrimarySceneController psc;

    private QuestManager qm;
    private QuestManagerTree qmt;
    private PlayerInfo playerInfo;

    public MainModel() {
        initialize(true);
    }

    public MainModel(PrimarySceneController psc){
        this.psc = psc;
        initialize(false);
    }

    private void initialize(boolean runningHeadless){
        this.runningHeadless = runningHeadless;
        playerInfo = new PlayerInfo(1);
        qm = new QuestManager(playerInfo);
        qmt = new QuestManagerTree(playerInfo);
    }

    public void recheckLockedQuests(){
        qm.doPrerequisiteQuestCheckForLocked();
    }

    public void incrementTraderLoyaltyLevel(TraderType traderType){
        playerInfo.incrementLoyaltyLevel(traderType);
        recheckLockedQuests(); //TODO reform DELETE
        qmt.playerInfoHasBeenUpdated(playerInfo);
    }

    public void incrementPlayerLevel(){
        playerInfo.incrementPlayerLevel();
        recheckLockedQuests(); //TODO reform DELETE
        qmt.playerInfoHasBeenUpdated(playerInfo);
    }

    public void loadSlot(int slotNumber){
        JSONParserHelper jph = new JSONParserHelper();
        SaveData saveData = jph.loadSlot(slotNumber);

        playerInfo.reload(saveData.playerInfo);
        //qm = new QuestManager(playerInfo);
        qm.reloadFromCompletedQuests(saveData.completedQuestIds, saveData.playerInfo);
        if(!runningHeadless) {
            psc.reloadPlayerInfoVisuals();
            psc.reloadQuestVisuals();
        }
    }

    public boolean saveSlot(int slotNumber){
        JSONParserHelper jph = new JSONParserHelper();
        boolean didSave = jph.SaveData(slotNumber, new ArrayList<>(qm.getCompleted()), playerInfo);
        if(didSave)
            System.out.println("Save successful!");
        else
            System.out.println("Save failed!");

        return didSave;
    }

    public QuestManager getQm() {
        return qm;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
