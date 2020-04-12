package dk.view;

import javafx.scene.layout.Pane;

public class PaneAndController {

    public Pane pane;
    public QuestCardController qcc;

    public PaneAndController() {
    }

    public PaneAndController(Pane pane, QuestCardController qcc) {
        this.pane = pane;
        this.qcc = qcc;
    }
}
