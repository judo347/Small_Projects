package dk.model;

public enum TraderType {

    PRAPOR("Prapor"), THERAPIST("Therapist"), FENCE("Fence"),
    SKIER("Skier"), PEACEKEEPER("Peacekeeper"), MECHANIC("Mechanic"),
    RAGMAN("Ragman"), JAEGER("Jaeger");

    private final String name;

    TraderType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
