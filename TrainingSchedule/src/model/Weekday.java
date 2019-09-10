package model;

public enum Weekday {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private String label;

    private Weekday(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static Weekday getWeekdayFromString(String string) {
        Weekday[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Weekday day = var1[var3];
            if (day.label.compareTo(string) == 0) {
                return day;
            }
        }

        throw new IllegalArgumentException();
    }
}
