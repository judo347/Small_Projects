package dk.model;

public enum MapType {

    CUSTOMS("Customs"), FACTORY("Factory"), INTERCHANGE("Interchange"),
    WOODS("Woods"), SHORELINE("Shoreline"), RESERVE("Reserve"), LABS("Labs"),
    MIXED("Mixed");

    private final String name;

    MapType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
