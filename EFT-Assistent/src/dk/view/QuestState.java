package dk.view;

public enum QuestState {
    ACCEPTED("B0FFA3"), AVAILABLE("FFFFFF"), LOCKED("878787");

    private String colorString;

    QuestState(String colorString) {
        this.colorString = colorString;
    }

    public String getColorString() {
        return colorString;
    }
}
