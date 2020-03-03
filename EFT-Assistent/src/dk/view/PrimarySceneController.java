package dk.view;

import dk.model.MapType;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PrimarySceneController {
    @FXML
    private HBox hbox_interchange_quests;

    @FXML
    private HBox hbox_labs_quests;

    @FXML
    private HBox hbox_customs_quests;

    @FXML
    private HBox hbox_woods_quests;

    @FXML
    private HBox hbox_mixed_quests;

    @FXML
    private HBox hbox_reserve_quests;

    @FXML
    private HBox hbox_factory_quests;

    @FXML
    private HBox hbox_shoreline_quests;

    public void addQuestCard(Pane questCard, MapType mapType){

        HBox desiredHBox;

        switch (mapType){
            case SHORELINE: desiredHBox = hbox_shoreline_quests;
                            break;
            case LABS: desiredHBox = hbox_labs_quests;
                            break;
            case MIXED: desiredHBox = hbox_mixed_quests;
                            break;
            case WOODS: desiredHBox = hbox_woods_quests;
                            break;
            case CUSTOMS: desiredHBox = hbox_customs_quests;
                            break;
            case FACTORY: desiredHBox = hbox_factory_quests;
                            break;
            case RESERVE: desiredHBox = hbox_reserve_quests;
                            break;
            case INTERCHANGE: desiredHBox = hbox_interchange_quests;
                            break;
            default: throw new IllegalArgumentException();
        }

        desiredHBox.getChildren().add(questCard);
    }
}
