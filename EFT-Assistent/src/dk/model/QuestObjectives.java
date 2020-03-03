package dk.model;

import java.util.ArrayList;

public class QuestObjectives {

    private String objective;
    private ArrayList<QuestObjectives> objectives;

    public QuestObjectives(String objective, ArrayList<QuestObjectives> objectives) {
        this.objective = objective;
        this.objectives = objectives;
    }

    public String getObjective() {
        return objective;
    }

    public ArrayList<QuestObjectives> getObjectives() {
        return objectives;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setObjectives(ArrayList<QuestObjectives> objectives) {
        this.objectives = objectives;
    }
}
