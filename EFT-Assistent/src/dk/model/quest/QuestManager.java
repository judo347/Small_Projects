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

    private final int quest_id_postman_pat_part1 = 7;
    private final int quest_id_postman_pat_part2 = 38;
    private final int quest_id_collector = 185;

    private final int quest_id_kind_of_sabotage = 63;
    private final int quest_id_supply_plans = 30;

    SpecialCaseChem4Helper specialCaseChem4Helper = new SpecialCaseChem4Helper(this);

    public QuestManager(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        allQuests = loadAllQuests();
        for(Quest quest : new ArrayList<>(allQuests)) {
            addQuestToManager(quest, playerInfo);
        }
    }

    /** Adds the given quest to activeQuests if requirements are met.
     * Should only be used as initializing and loading. */
    private void addQuestToManager(Quest quest, PlayerInfo playerInfo){

        if(specialCaseChem4Helper.addQuestCheck(quest)) { }
        else if(canQuestBeAddedToActive(quest, playerInfo))
            activeQuests.add(quest);
        else
            lockedQuests.add(quest);
    }

    /** Returns true if quest fulfills all requirements for being active. */
    private boolean canQuestBeAddedToActive(Quest quest, PlayerInfo playerInfo){

        // Special case: quest: collector TODO handle
        if(quest.getId() == quest_id_collector)
            //method for check
            return false;

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
                moveQuestFromLockedToActive(quest);
                wasListUpdated = true;
                break;
            }
        }

        if(wasListUpdated)
            doPrerequisiteQuestCheckForLocked();
    }

    /** Used when a quest becomes active. */
    private void moveQuestFromLockedToActive(Quest quest){
        lockedQuests.remove(quest);
        activeQuests.add(quest);

        //Special case 1
        specialCaseChem4Helper.chem4FromLockedToActiveCheck(quest);
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
        int givenQuestId = quest.getId();

        //Special case: quest: postman pat
        if (givenQuestId == quest_id_postman_pat_part1){
            if (!canPostmanPatPart1BeCompleted())
                return;
        }

        //Special case: quest: supply plans or kind of sabotage
        specialCaseSupplyPlansAndKindOfSabotageCompleteCheck(quest);

        //Special case 1
        specialCaseChem4Helper.primaryCompleteCheck(quest);

        quest.complete();
        activeQuests.remove(quest);
        completed.add(quest);

        doPrerequisiteQuestCheckForLocked();
    }

    /** Special case: quest: Supply plans and Kind of sabotage: one completes the other. */
    private void specialCaseSupplyPlansAndKindOfSabotageCompleteCheck(Quest quest){
        if(quest.getId() == quest_id_supply_plans){
            boolean questFound = false;
            for(Quest q : new ArrayList<>(activeQuests)){
                if (q.getId() == quest_id_kind_of_sabotage){
                    q.complete();
                    activeQuests.remove(q);
                    completed.add(q);
                    questFound = true;
                }

                if (questFound)
                    break;
            }
        }else if(quest.getId() == quest_id_kind_of_sabotage){
            boolean questFound = false;
            for(Quest q : new ArrayList<>(activeQuests)){
                if (q.getId() == quest_id_supply_plans){
                    q.complete();
                    activeQuests.remove(q);
                    completed.add(q);
                    questFound = true;
                }

                if (questFound)
                    break;
            }
        }
    }

    /** Special case: quest: postman pat
        Postman pat part 1 - cannot be completed before part 2 is */
    private boolean canPostmanPatPart1BeCompleted(){
        // Check: has postman pat part 2 is completed
        for(Quest quest : completed){
            if (quest.getId() == quest_id_postman_pat_part2)
                return true;
        }

        return false;
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
