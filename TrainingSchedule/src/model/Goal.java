package model;

import javafx.beans.property.*;

public class Goal {

    private final StringProperty description = new SimpleStringProperty("");
    private final SimpleFloatProperty plannedDistance = new SimpleFloatProperty();
    private final FloatProperty completedDistance = new SimpleFloatProperty();
    private final IntegerProperty plannedMinutes = new SimpleIntegerProperty();
    private final IntegerProperty completedMinutes = new SimpleIntegerProperty();
    private final StringProperty plannedWeekday = new SimpleStringProperty("");
    private final StringProperty completedWeekday = new SimpleStringProperty("");

    //private String description = "";
    //private Float plannedDistance;
    //private Float completedDistance;
    //private Integer plannedMinutes;
    //private Integer completedMinutes;
    //private Weekday plannedWeekday;
    //private Weekday completedWeekday;

    public Goal(String description, Float plannedDistance, Float completedDistance, Integer plannedMinutes, Integer completedMinutes, Weekday plannedWeekday, Weekday completedWeekday) {
        //this.description = description;
        //setDescription(description);
        //this.description = new SimpleStringProperty(description);
        //this.description.set(description);
        setDescription(description);
        setPlannedDistance(plannedDistance);
        setCompletedDistance(completedDistance);
        setPlannedMinutes(plannedMinutes);
        setPlannedWeekday(plannedWeekday);
        setCompletedWeekday(completedWeekday);
        //this.completedDistance = completedDistance;
        //this.plannedMinutes = plannedMinutes;
        //this.completedMinutes = completedMinutes;
        //this.plannedWeekday = plannedWeekday;
        //this.completedWeekday = completedWeekday;
    }

    public Goal() {
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public Float getPlannedDistance() {
        return plannedDistance.get();
    }

    public SimpleFloatProperty plannedDistanceProperty() {
        return plannedDistance;
    }

    public float getCompletedDistance() {
        return completedDistance.get();
    }

    public FloatProperty completedDistanceProperty() {
        return completedDistance;
    }

    public int getPlannedMinutes() {
        return plannedMinutes.get();
    }

    public IntegerProperty plannedMinutesProperty() {
        return plannedMinutes;
    }

    public int getCompletedMinutes() {
        return completedMinutes.get();
    }

    public IntegerProperty completedMinutesProperty() {
        return completedMinutes;
    }

    public String getPlannedWeekday() {
        return plannedWeekday.get();
    }

    public StringProperty plannedWeekdayProperty() {
        return plannedWeekday;
    }

    public String getCompletedWeekday() {
        return completedWeekday.get();
    }

    public StringProperty completedWeekdayProperty() {
        return completedWeekday;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setPlannedDistance(float plannedDistance) {
        this.plannedDistance.set(plannedDistance);
    }

    public void setCompletedDistance(float completedDistance) {
        this.completedDistance.set(completedDistance);
    }

    public void setPlannedMinutes(int plannedMinutes) {
        this.plannedMinutes.set(plannedMinutes);
    }

    public void setCompletedMinutes(int completedMinutes) {
        this.completedMinutes.set(completedMinutes);
    }

    public void setPlannedWeekday(Weekday plannedWeekday) {
        this.plannedWeekday.set(plannedWeekday.getLabel());
    }

    public void setCompletedWeekday(Weekday completedWeekday) {
        this.completedWeekday.set(completedWeekday.getLabel());
    }
}
