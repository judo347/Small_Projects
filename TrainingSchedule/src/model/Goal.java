package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Goal {
    private final StringProperty description = new SimpleStringProperty("");
    private final StringProperty plannedDistance = new SimpleStringProperty("");
    private final StringProperty completedDistance = new SimpleStringProperty("");
    private final StringProperty plannedMinutes = new SimpleStringProperty("");
    private final StringProperty completedMinutes = new SimpleStringProperty("");
    private final StringProperty plannedWeekday = new SimpleStringProperty("");
    private final StringProperty completedWeekday = new SimpleStringProperty("");

    public Goal(String description, String plannedDistance, String completedDistance, String plannedMinutes, String completedMinutes, Weekday plannedWeekday, Weekday completedWeekday) {
        this.setDescription(description);
        this.setPlannedDistance(plannedDistance);
        this.setCompletedDistance(completedDistance);
        this.setPlannedMinutes(plannedMinutes);
        this.setCompletedMinutes(completedMinutes);
        this.setPlannedWeekday(plannedWeekday);
        this.setCompletedWeekday(completedWeekday);
    }

    public Goal() {
    }

    public StringProperty descriptionProperty() {
        return this.description;
    }

    public StringProperty plannedDistanceProperty() {
        return this.plannedDistance;
    }

    public StringProperty completedDistanceProperty() {
        return this.completedDistance;
    }

    public StringProperty plannedMinutesProperty() {
        return this.plannedMinutes;
    }

    public StringProperty completedMinutesProperty() {
        return this.completedMinutes;
    }

    public StringProperty plannedWeekdayProperty() {
        return this.plannedWeekday;
    }

    public StringProperty completedWeekdayProperty() {
        return this.completedWeekday;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setPlannedDistance(String plannedDistance) {
        this.plannedDistance.set(plannedDistance);
    }

    public void setCompletedDistance(String completedDistance) {
        this.completedDistance.set(completedDistance);
    }

    public void setPlannedMinutes(String plannedMinutes) {
        this.plannedMinutes.set(plannedMinutes);
    }

    public void setCompletedMinutes(String completedMinutes) {
        this.completedMinutes.set(completedMinutes);
    }

    public void setPlannedWeekday(Weekday plannedWeekday) {
        this.plannedWeekday.set(plannedWeekday.getLabel());
    }

    public void setCompletedWeekday(Weekday completedWeekday) {
        this.completedWeekday.set(completedWeekday.getLabel());
    }

    public String getDescription() {
        return (String)this.description.get();
    }

    public String getPlannedDistance() {
        return (String)this.plannedDistance.get();
    }

    public String getCompletedDistance() {
        return (String)this.completedDistance.get();
    }

    public String getPlannedMinutes() {
        return (String)this.plannedMinutes.get();
    }

    public String getCompletedMinutes() {
        return (String)this.completedMinutes.get();
    }

    public String getPlannedWeekday() {
        return (String)this.plannedWeekday.get();
    }

    public String getCompletedWeekday() {
        return (String)this.completedWeekday.get();
    }
}