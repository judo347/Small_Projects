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

    public String getMondayDateString(){

        StringBuilder sb = new StringBuilder();

        sb.append(mondayDate.get(Calendar.DAY_OF_MONTH));
        sb.append("-");
        sb.append(mondayDate.get(Calendar.MONTH) + 1);
        sb.append("-");
        sb.append(mondayDate.get(Calendar.YEAR));

        return sb.toString();
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void removeGoal(Goal goal){
        goals.remove(goal);
    }

    @Override
    public String toString() {
        return "Week " + weekNumber;
    }
}
