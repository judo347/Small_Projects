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

    public static Weekday getWeekdayFromString(String string){

        for(Weekday day : Weekday.values()){
            if(day.label.compareTo(string) == 0)
                return day;
        }

        throw new IllegalArgumentException(); //String could not be resolved to Weekday
    }
}
