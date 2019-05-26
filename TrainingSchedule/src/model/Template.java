package model;

import java.util.ArrayList;

public class Template {

    private ArrayList<Goal> goals;
    private String templateName;

    public Template(ArrayList<Goal> goals, String templateName) {
        this.goals = goals;
        this.templateName = templateName;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public String getTemplateName() {
        return templateName;
    }
}
