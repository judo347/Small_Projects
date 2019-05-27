package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Week {

    private Calendar mondayDate;
    private ArrayList<Goal> goals;
    private int weekNumber;

    public Week(Calendar mondayDate, ArrayList<Goal> goals) {
        this.mondayDate = mondayDate;
        this.goals = goals;
        this.weekNumber = mondayDate.get(Calendar.WEEK_OF_YEAR);
    }

    public Week(Calendar mondayDate) {
        this(mondayDate, new ArrayList<>());
    }

    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public Calendar getMondayDate() {
        return mondayDate;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public int getWeekNumber() {
        return weekNumber;
    }
}
