package dk.view;

import javafx.scene.layout.Pane;

public class PaneAndController {

    public Pane pane;
    public QuestCardV2Controller qcc;

    public PaneAndController() {
    }

    public PaneAndController(Pane pane, QuestCardV2Controller qcc) {
        this.pane = pane;
        this.qcc = qcc;
    }
}
