package model;

import java.util.ArrayList;

public class Model {

    private ArrayList<Week> weeks;
    private ArrayList<Template> templates;
    private Week selectedWeek = null;

    public Model(ArrayList<Week> weeks, ArrayList<Template> templates) {
        this.templates = templates;
        this.weeks = weeks;
    }

    /** Creates a new blank goal. */
    public void createNewGoal(){
        //TODO
    }

    public ArrayList<Template> getTemplates() {
        return templates;
    }

    public ArrayList<Week> getWeeks() {
        return weeks;
    }

    public Week getSelectedWeek() {
        return selectedWeek;
    }

    public void setSelectedWeek(Week selectedWeek) {
        this.selectedWeek = selectedWeek;
    }
}
