package model;

public class Goal {

    private String description = "";
    private Float plannedDistance;
    private Float completedDistance;
    private Integer plannedMinutes;
    private Integer completedMinutes;
    private Weekday plannedWeekday;
    private Weekday completedWeekday;

    public Goal(String description, Float plannedDistance, Float completedDistance, Integer plannedMinutes, Integer completedMinutes, Weekday plannedWeekday, Weekday completedWeekday) {
        this.description = description;
        this.plannedDistance = plannedDistance;
        this.completedDistance = completedDistance;
        this.plannedMinutes = plannedMinutes;
        this.completedMinutes = completedMinutes;
        this.plannedWeekday = plannedWeekday;
        this.completedWeekday = completedWeekday;
    }

    public Goal() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlannedDistance(Float plannedDistance) {
        this.plannedDistance = plannedDistance;
    }

    public void setCompletedDistance(Float completedDistance) {
        this.completedDistance = completedDistance;
    }

    public void setPlannedMinutes(Integer plannedMinutes) {
        this.plannedMinutes = plannedMinutes;
    }

    public void setCompletedMinutes(Integer completedMinutes) {
        this.completedMinutes = completedMinutes;
    }

    public void setPlannedWeekday(Weekday plannedWeekday) {
        this.plannedWeekday = plannedWeekday;
    }

    public void setCompletedWeekday(Weekday completedWeekday) {
        this.completedWeekday = completedWeekday;
    }
}
