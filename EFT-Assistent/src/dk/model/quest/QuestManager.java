package dk.model.quest;

import dk.model.PlayerInfo;
import dk.model.TraderType;
import dk.model.quest.Quest;
import dk.view.QuestInstantiator;
import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;

public class QuestManager {

    private QuestInstantiator qi = new QuestInstantiator();

    private ArrayList<Quest> completed = new ArrayList<>();
    private ArrayList<Quest> activeQuests = new ArrayList<>();
    private ArrayList<Quest> lockedQuests = new ArrayList<>();

    public QuestManager() {

    }

    public void loadQuests(PlayerInfo playerInfo){
        for(Quest quest : qi.allQuests){
            addQuest(quest, playerInfo);
        }
    }

    public void addQuest(Quest quest, PlayerInfo playerInfo){


        if(quest.getRequiredLevel() > playerInfo.getPlayerLevel()){
            lockedQuests.add(quest);
            return;
        }

        //Handle LL requirements for quests
        TraderType questGiver = quest.getTrader();
        int currentLoyaltyLevelForTrader = playerInfo.getLoyaltyLevelFromTrader(questGiver);

        if(quest.getRequiredLoyaltyLevel() > currentLoyaltyLevelForTrader){
            lockedQuests.add(quest);
            return;
        }

        //Handle prerequisite quests
        if(!isRequiredQuestsCompleted(quest)){
            lockedQuests.add(quest);
            return;
        }

        activeQuests.add(quest);
    }

    public void doPrerequisiteQuestCheckForLocked(){
        for(Quest quest : new ArrayList<>(lockedQuests)){
            boolean shouldBeActive = isRequiredQuestsCompleted(quest);
            if(shouldBeActive){
                lockedQuests.remove(quest);
                activeQuests.add(quest);
            }
        }
    }

    //TODO Can be optimized by a lot!!
    private boolean isRequiredQuestsCompleted(Quest quest){
        ArrayList<Integer> requiredQuests = quest.getRequiredQuestIds();
        ArrayList<Boolean> accepted = new ArrayList<>();

        for(int reqQuestId : requiredQuests){
            boolean questisCompleted = false;
            for(Quest compQuest : completed){
                if(reqQuestId == compQuest.getId()){
                    questisCompleted = true;
                    accepted.add(questisCompleted);
                }
            }
        }

        return requiredQuests.size() == accepted.size();
    }

    public void completeQuest(Quest quest){
        quest.complete();
        activeQuests.remove(quest);
        completed.add(quest);

        doPrerequisiteQuestCheckForLocked();
    }


    public void reloadFromCompletedQuests(ArrayList<Integer> completedQuestIds, PlayerInfo playerInfo){
        QuestLoader ql = new QuestLoader();
        ArrayList<Quest> allQuests = ql.allQuest;

        //Remove completed quests
        for(Quest quest : new ArrayList<>(allQuests))
            for(int id : completedQuestIds)
                if(quest.getId() == id)
                    allQuests.remove(quest);

        //Load with remaining quests
        for(Quest quest : allQuests){
            addQuest(quest, playerInfo);
        }

        doPrerequisiteQuestCheckForLocked(); //TODO Should run until nothing happens??
    }

    public QuestInstantiator getQi() {
        return qi;
    }

    public ArrayList<Quest> getActiveQuests() {
        return activeQuests;
    }

    public ArrayList<Quest> getCompleted() {
        return completed;
    }
}
