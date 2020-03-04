package dk.mk.tools;

import java.awt.*;

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

    /** Makes the mouse click the position of the given enum. */
    public void clickPos(ClickPositions cPosType){
        clickPos(cPosType.x, cPosType.y);
    }

    /** Makes the mouse click the given position. */
    public void clickPos(int x, int y){

    }

    /** Moves the mouse to the given position. */
    private void moveMouse(int x, int y){
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void clickMouseAtCurrentPos(){

    }
}
