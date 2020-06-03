package dk.model;

import dk.Main;
import dk.data.JSONParserHelper;
import dk.model.quest.Quest;
import dk.model.quest.QuestManager;
import dk.model.quest.QuestManagerTree;
import dk.view.PrimarySceneController;

import java.util.ArrayList;

public class MainModel {

    private boolean runningHeadless;

    private PrimarySceneController psc;

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
        qmt = new QuestManagerTree(playerInfo);
    }

    public void recheckLockedQuests(){
        throw new IllegalArgumentException("Should not be called!");
    }

    public void completeQuest(Quest quest){
        qmt.completeQuest(quest, playerInfo);
    }

    public void incrementTraderLoyaltyLevel(TraderType traderType){
        playerInfo.incrementLoyaltyLevel(traderType);
        qmt.playerInfoHasBeenUpdated(playerInfo);
    }

    public void incrementPlayerLevel(){
        playerInfo.incrementPlayerLevel();
        qmt.playerInfoHasBeenUpdated(playerInfo);
    }

    public void loadSlot(int slotNumber){
        JSONParserHelper jph = new JSONParserHelper();
        SaveData saveData = jph.loadSlot(slotNumber);

        playerInfo.reload(saveData.playerInfo);
        qmt.reloadFromCompletedQuests(saveData.playerInfo, saveData.completedQuestIds);
        if(!runningHeadless) {
            psc.reloadPlayerInfoVisuals();
            psc.reloadQuestVisuals();
        }
    }

    public boolean saveSlot(int slotNumber){
        JSONParserHelper jph = new JSONParserHelper();
        boolean didSave = jph.SaveData(slotNumber, new ArrayList<>(qmt.getCompletedQuests()), playerInfo);
        if(didSave)
            System.out.println("Save successful!");
        else
            System.out.println("Save failed!");

        return didSave;
    }

    public QuestManagerTree getQmt(){
        return qmt;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}
