package model;

public enum Weekday {
    MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY("Sunday");

    private String label;

    Weekday(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
