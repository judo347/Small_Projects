package dk.model.quest;

import dk.model.MapType;
import dk.model.TraderType;

import java.util.ArrayList;

public class Quest {

    private String questName;
    private ArrayList<MapType> maps = new ArrayList<>(); //Map which the quest can be completed on //May be 0, 1 or many
    private TraderType trader; //Quest giver
    private int requiredLevel;
    private int requiredLoyaltyLevel;
    private int id; //unique id
    private ArrayList<Integer> requiredQuests;

    private ArrayList<QuestObjectives> objectives;
    private ArrayList<String> requirements;

    private boolean completed = false;

    public Quest(String questName, ArrayList<MapType> maps, TraderType trader, int requiredLevel, ArrayList<QuestObjectives> objectives, ArrayList<String> requirements, int requiredLoyaltyLevel, int id, ArrayList<Integer> requiredQuests) {
        this.questName = questName;
        this.maps = maps;
        this.trader = trader;
        this.requiredLevel = requiredLevel;
        this.objectives = objectives;
        this.requirements = requirements;
        this.requiredLoyaltyLevel = requiredLoyaltyLevel;
        this.id = id;
        this.requiredQuests = requiredQuests;
    }

    public int getRequiredLoyaltyLevel() {
        return requiredLoyaltyLevel;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getQuestName() {
        return questName;
    }

    public ArrayList<MapType> getMaps() {
        return maps;
    }

    public TraderType getTrader() {
        return trader;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public ArrayList<QuestObjectives> getObjectives() {
        return objectives;
    }

    public ArrayList<String> getRequirements() {
        return requirements;
    }

    public void complete(){
        completed = true;
    }
}
