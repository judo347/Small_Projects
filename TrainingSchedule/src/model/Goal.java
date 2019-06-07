package model;

import javafx.beans.property.*;

public class Goal {

    private final StringProperty description = new SimpleStringProperty("");
    private final StringProperty plannedDistance = new SimpleStringProperty("");
    private final StringProperty completedDistance = new SimpleStringProperty("");
    private final StringProperty plannedMinutes = new SimpleStringProperty("");
    private final StringProperty completedMinutes = new SimpleStringProperty("");
    private final StringProperty plannedWeekday = new SimpleStringProperty("");
    private final StringProperty completedWeekday = new SimpleStringProperty("");

    public Goal(String description, String plannedDistance, String completedDistance, String plannedMinutes, String completedMinutes, Weekday plannedWeekday, Weekday completedWeekday) {
        setDescription(description);
        setPlannedDistance(plannedDistance);
        setCompletedDistance(completedDistance);
        setPlannedMinutes(plannedMinutes);
        setCompletedMinutes(completedMinutes);
        setPlannedWeekday(plannedWeekday);
        setCompletedWeekday(completedWeekday);
    }

    public Goal() {
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty plannedDistanceProperty() {
        return plannedDistance;
    }

    public StringProperty completedDistanceProperty() {
        return completedDistance;
    }

    public StringProperty plannedMinutesProperty() {
        return plannedMinutes;
    }

    public StringProperty completedMinutesProperty() {
        return completedMinutes;
    }

    public StringProperty plannedWeekdayProperty() {
        return plannedWeekday;
    }

    public StringProperty completedWeekdayProperty() {
        return completedWeekday;
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
        return description.get();
    }

    public String getPlannedDistance() {
        return plannedDistance.get();
    }

    public String getCompletedDistance() {
        return completedDistance.get();
    }

    public String getPlannedMinutes() {
        return plannedMinutes.get();
    }

    public String getCompletedMinutes() {
        return completedMinutes.get();
    }

    public String getPlannedWeekday() {
        return plannedWeekday.get();
    }

    public String getCompletedWeekday() {
        return completedWeekday.get();
    }
}
