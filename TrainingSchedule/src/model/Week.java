package model;

import java.util.ArrayList;
import java.util.Date;

public class Week {

    private Date mondayDate;
    private ArrayList<Goal> goals;
    private int weekNumber;

    public Week(Date mondayDate, ArrayList<Goal> goals, int weekNumber) {
        this.mondayDate = mondayDate;
        this.goals = goals;
        this.weekNumber = weekNumber;
    }

    public Week(Date mondayDate) {
        this.mondayDate = mondayDate;
        this.goals = new ArrayList<>();
        //TODO Calculate week number
    }

    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public Date getMondayDate() {
        return mondayDate;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public int getWeekNumber() {
        return weekNumber;
    }
}
