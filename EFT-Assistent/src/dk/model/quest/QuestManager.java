package dk.model.quest;

import dk.data.JSONParserHelper;
import dk.model.PlayerInfo;
import dk.model.TraderType;

import java.util.ArrayList;

public class QuestManager {

    private ArrayList<Quest> allQuests;
    private PlayerInfo playerInfo;

    private ArrayList<Quest> completed = new ArrayList<>();
    private ArrayList<Quest> activeQuests = new ArrayList<>();
    private ArrayList<Quest> lockedQuests = new ArrayList<>();

    public QuestManager(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        allQuests = loadAllQuests();
        for(Quest quest : new ArrayList<>(allQuests)) {
            addQuestToManager(quest, playerInfo);
        }
    }

    /** Adds the given quest to activeQuests if requirements are met. */
    private void addQuestToManager(Quest quest, PlayerInfo playerInfo){
        if(canQuestBeAddedToActive(quest, playerInfo))
            activeQuests.add(quest);
        else
            lockedQuests.add(quest);
    }

    /** Returns true if quest fulfills all requirements for being active. */
    private boolean canQuestBeAddedToActive(Quest quest, PlayerInfo playerInfo){
        // Check player level requirement
        if(quest.getRequiredLevel() > playerInfo.getPlayerLevel()){
            return false;
        }

        //Handle LL requirements for quests
        TraderType questGiver = quest.getTrader();
        int currentLoyaltyLevelForTrader = playerInfo.getLoyaltyLevelFromTrader(questGiver);
        if(quest.getRequiredLoyaltyLevel() > currentLoyaltyLevelForTrader){
            return false;
        }

        //Handle prerequisite quests
        if(!isRequiredQuestsCompleted(quest)){
            return false;
        }

        return true;
    }

    /** Moves *locked* quests which fulfills checks to *active*. */
    public void doPrerequisiteQuestCheckForLocked(){
        boolean wasListUpdated = false;

        for(Quest quest : new ArrayList<>(lockedQuests)){
            boolean shouldBeActive = canQuestBeAddedToActive(quest, playerInfo);
            if(shouldBeActive){
                lockedQuests.remove(quest);
                activeQuests.add(quest);
                wasListUpdated = true;
                break;
            }
        }

        if(wasListUpdated)
            doPrerequisiteQuestCheckForLocked();
    }

    /** Compares the given quest´s required quests with completed quests. */
    private boolean isRequiredQuestsCompleted(Quest quest){
        ArrayList<Integer> requiredQuestIds = quest.getRequiredQuestIds();

        for(Quest compQuest : completed){
            for(Integer regQuestId : requiredQuestIds){
                if(compQuest.getId() == regQuestId)
                    requiredQuestIds.remove(regQuestId);
                    break;
            }
        }

        return requiredQuestIds.size() == 0;
    }

    /** Completes given quest and updates model. (Including prerequisite check)*/
    public void completeQuest(Quest quest){
        quest.complete();
        activeQuests.remove(quest);
        completed.add(quest);

        doPrerequisiteQuestCheckForLocked();
    }

    /** Reloads quest model based on the given completed quest ids. (Sets active and locked)
     Used when loading saved data. */
    public void reloadFromCompletedQuests(ArrayList<Integer> completedQuestIds, PlayerInfo playerInfo){
        ArrayList<Quest> allQuests = loadAllQuests();

        //Remove completed quests
        for(Quest quest : new ArrayList<>(allQuests))
            for(int id : completedQuestIds)
                if(quest.getId() == id)
                    allQuests.remove(quest);

        //Load with remaining quests
        for(Quest quest : allQuests){
            addQuestToManager(quest, playerInfo);
        }

        doPrerequisiteQuestCheckForLocked();
    }

    /** Loads all quests from data file. */
    private ArrayList<Quest> loadAllQuests(){
        JSONParserHelper jph = new JSONParserHelper();
        return jph.getAllQuestsFromFile();
    }

    public ArrayList<Quest> getActiveQuests() {
        return activeQuests;
    }

    public ArrayList<Quest> getCompleted() {
        return completed;
    }

    public int getNumberOfCompletedQuests(){
        return completed.size();
    }

    public int getTotalNumberOfQuests(){
        return allQuests.size();
    }
}
