package dk.mk.tools;

public class ScreenClicker {

    public enum ClickPositions {
        MENU_TO_FLEA(0, 0),
        MENU_TO_VENDOR(0,0),
        VENDOR_TO_PRAPOR(0,0),
        VENDOR_TO_THERAPIST(0, 0),
        VENDOR_TO_FENCE(0, 0),
        VENDOR_TO_SKIER(0, 0),
        VENDOR_TO_PEACEKEEPER(0, 0),
        VENDOR_TO_RAGMAN(0, 0),
        VENDOR_TO_JEAGER(0, 0),
        VENDOR_SELLTAB(0, 0),
        VENDOR_SELLTAB_DEAL(0, 0),
        FLEA_REFRESH(0, 0);

        private final int x;
        private final int y;

        private ClickPositions(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
