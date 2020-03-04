package dk.model;

import java.util.ArrayList;

public class QuestObjectives {

    private String objective;
    //private ArrayList<QuestObjectives> objectives;
    private ArrayList<String> subObjectives;

    public QuestObjectives(String objective, ArrayList<String> subObjectives) {
        this.objective = objective;
        this.subObjectives = subObjectives;
    }

    public QuestObjectives() {
    }

    public String getObjective() {
        return objective;
    }

    public ArrayList<String> getSubObjectives() {
        return subObjectives;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setSubObjectives(ArrayList<String> subObjectives) {
        this.subObjectives = subObjectives;
    }
}
