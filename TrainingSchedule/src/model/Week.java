package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Week {
    private Calendar mondayDate;
    private ArrayList<Goal> goals;
    private int weekNumber;

    public Week(Calendar mondayDate, ArrayList<Goal> goals) {
        this.mondayDate = mondayDate;
        this.goals = goals;
        this.calculateWeekNumber();
    }

    private void calculateWeekNumber() {
        this.weekNumber = this.mondayDate.get(3);
    }

    public Week(Calendar mondayDate) {
        this(mondayDate, new ArrayList());
    }

    public void addGoal(Goal goal) {
        this.goals.add(goal);
    }

    public Calendar getMondayDate() {
        return this.mondayDate;
    }

    public String getMondayDateString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mondayDate.get(5));
        sb.append("-");
        sb.append(this.mondayDate.get(2) + 1);
        sb.append("-");
        sb.append(this.mondayDate.get(1));
        return sb.toString();
    }

    public ArrayList<Goal> getGoals() {
        return this.goals;
    }

    public int getWeekNumber() {
        return this.weekNumber;
    }

    public void removeGoal(Goal goal) {
        this.goals.remove(goal);
    }

    public String toString() {
        return String.valueOf(this.weekNumber);
    }

    public void incrementWeekNumber() {
        this.mondayDate.add(6, 7);
        this.calculateWeekNumber();
    }

    public void decrementWeekNumber() {
        this.mondayDate.add(6, -7);
        this.calculateWeekNumber();
    }
}
