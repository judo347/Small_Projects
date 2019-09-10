package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Model {
    private ArrayList<Week> weeks;
    private ArrayList<Template> templates;

    public Model(ArrayList<Week> weeks, ArrayList<Template> templates) {
        this.templates = templates;
        this.weeks = weeks;
    }

    public void createNewGoal() {
    }

    public ArrayList<Template> getTemplates() {
        return this.templates;
    }

    public ArrayList<Week> getWeeks() {
        return this.weeks;
    }

    public ArrayList<Goal> getGoalsOfWeek(Week requestedWeek) {
        Iterator var2 = this.weeks.iterator();

        Week week;
        do {
            if (!var2.hasNext()) {
                throw new IllegalArgumentException();
            }

            week = (Week)var2.next();
        } while(requestedWeek != week);

        return week.getGoals();
    }

    public void removeWeek(Week week) {
        this.weeks.remove(week);
    }
}
