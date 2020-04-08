package dk.view;

public enum QuestState {
    ACCEPTED("00FF11"), AVAILABLE("96BC25"), LOCKED("404040");

    private String colorString;

    QuestState(String colorString) {
        this.colorString = colorString;
    }

    public String getColorString() {
        return colorString;
    }
}
