package model;

import java.util.ArrayList;

public class Model {

    private ArrayList<Template> templates;
    private ArrayList<Week> weeks;
    private Week selectedWeek;

    //TODO Contructor

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
