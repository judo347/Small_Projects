package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Goal {

    private final StringProperty description = new SimpleStringProperty("");
    private final FloatProperty plannedDistance = new SimpleFloatProperty();

    //private String description = "";
    //private Float plannedDistance;
    private Float completedDistance;
    private Integer plannedMinutes;
    private Integer completedMinutes;
    private Weekday plannedWeekday;
    private Weekday completedWeekday;

    public Goal(String description, Float plannedDistance, Float completedDistance, Integer plannedMinutes, Integer completedMinutes, Weekday plannedWeekday, Weekday completedWeekday) {
        //this.description = description;
        //setDescription(description);
        //this.description = new SimpleStringProperty(description);
        //this.description.set(description);
        setDescription(description);
        setPlannedDistance(plannedDistance);
        this.completedDistance = completedDistance;
        this.plannedMinutes = plannedMinutes;
        this.completedMinutes = completedMinutes;
        this.plannedWeekday = plannedWeekday;
        this.completedWeekday = completedWeekday;
    }

    public Goal() {
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setPlannedDistance(Float plannedDistance) {
        this.plannedDistance.set(plannedDistance);
    }

    public FloatProperty getPlannedDistanceProperty() {
        return plannedDistance;
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
