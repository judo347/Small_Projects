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

        return true; //Todo: was false. Workaround = true
    }

    /** Reloads quest model based on the given completed quest ids. (Sets active and locked)
     Used when loading saved data. */
    public void reloadFromCompletedQuests(ArrayList<Integer> completedQuestIds, PlayerInfo playerInfo){
        // Currently done before method call
        //allQuests = loadAllQuests();
        //activeQuests = new ArrayList<>();
        //completed = new ArrayList<>();

        //Find all quests which has to be completed
        //TODO optimize, dictionary for quests?
        ArrayList<Quest> questsToComplete = new ArrayList<>();
        for(Quest quest : allQuests)
            for(Integer complId : completedQuestIds)
                if (quest.getId() == complId){
                    questsToComplete.add(quest);
                    break;
                }

        for(Quest quest : questsToComplete){
            completeQuestRecursively(quest, playerInfo);
        }
    }

    //TODO Write tests for special cases generally
    private void completeQuestRecursively(Quest quest, PlayerInfo playerInfo){

        if (!quest.isCompleted()){
            //TODO make quests have refs to each other
            ArrayList<Quest> requiredQuests = new ArrayList<>();
            for(Integer regQuestId : quest.getRequiredQuestIds()){
                for(Quest allQ : allQuests){
                    if(allQ.getId() == regQuestId){
                        requiredQuests.add(allQ);
                        break;
                    }
                }
            }
            if (requiredQuests.size() != quest.getRequiredQuestIds().size())
                throw new IllegalArgumentException("Did not find all required quests"); //TODO Is proberly thrown if quest required is special case which is saved in different class

            //Call method on all required quests for this one
            for (Quest q : requiredQuests)
                completeQuestRecursively(q, playerInfo);

            if(!canQuestBeAddedToActive(quest, playerInfo)){
                System.out.println(quest.getId() + " " + quest.getRequiredLevel() + " " + playerInfo.getPlayerLevel());
                //TODO debug note: quests is here because it should be completed. But when this is hit, the prereuistite quests are not completed yet??
                throw new IllegalArgumentException("Something went wrong!");  //TODO This will be throw if player loads a save where Collector is completed
            }

            completeQuest(quest);
        }
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

    public ArrayList<Quest> getLockedQuests() {
        return lockedQuests;
    }

    public int getNumberOfCompletedQuests(){
        return completed.size();
    }

    public int getTotalNumberOfQuests(){
        return allQuests.size();
    }
}
