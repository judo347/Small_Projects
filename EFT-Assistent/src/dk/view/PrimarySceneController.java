package dk.view;

import dk.model.MainModel;
import dk.model.MapType;
import dk.model.TraderType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class PrimarySceneController {

    @FXML
    private Label label_level_player;

    @FXML
    private Label label_level_prapor;

    @FXML
    private Label label_level_therapist;

    @FXML
    private Label label_level_skier;

    @FXML
    private Label label_level_peacekeeper;

    @FXML
    private Label label_level_mechanic;

    @FXML
    private Label label_level_ragman;

    @FXML
    private Label label_level_jaeger;

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

    private MainModel mainModel;

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void addQuestCard(PaneAndController questCardAndController, MapType mapType){

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

        desiredHBox.getChildren().add(questCardAndController.pane);
        questCardAndController.qcc.addBoxParent(desiredHBox, questCardAndController.pane);

        //hbox_interchange_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        //hbox_labs_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        //hbox_customs_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        //hbox_woods_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        //hbox_mixed_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        //hbox_reserve_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        //hbox_factory_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        //hbox_shoreline_quests.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
    }

    public void removeQuestCard(QuestCardController qcc, HBox box, Pane layoutComponent){
        box.getChildren().remove(layoutComponent);
    }

    @FXML
    void buttonAction_plus_player(MouseEvent event) {
        mainModel.incrementPlayerLevel();
        label_level_player.setText(String.valueOf(mainModel.getPlayerInfo().getPlayerLevel()));
    }

    @FXML
    void buttonAction_plus_prapor(MouseEvent event) {
        mainModel.incrementTraderLoyaltyLevel(TraderType.PRAPOR);
        label_level_prapor.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.PRAPOR)));
    }

    @FXML
    void buttonAction_plus_therapist(MouseEvent event) {
        mainModel.incrementTraderLoyaltyLevel(TraderType.THERAPIST);
        label_level_therapist.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.THERAPIST)));
    }

    @FXML
    void buttonAction_plus_skier(MouseEvent event) {
        mainModel.incrementTraderLoyaltyLevel(TraderType.SKIER);
        label_level_skier.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.SKIER)));
    }

    @FXML
    void buttonAction_plus_peacekeeper(MouseEvent event) {
        mainModel.incrementTraderLoyaltyLevel(TraderType.PEACEKEEPER);
        label_level_peacekeeper.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.PEACEKEEPER)));
    }

    @FXML
    void buttonAction_plus_mechanic(MouseEvent event) {
        mainModel.incrementTraderLoyaltyLevel(TraderType.MECHANIC);
        label_level_mechanic.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.MECHANIC)));
    }

    @FXML
    void buttonAction_plus_ragman(MouseEvent event) {
        mainModel.incrementTraderLoyaltyLevel(TraderType.RAGMAN);
        label_level_ragman.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.RAGMAN)));
    }

    @FXML
    void buttonAction_plus_jaeger(MouseEvent event) {
        mainModel.incrementTraderLoyaltyLevel(TraderType.JAEGER);
        label_level_jaeger.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.JAEGER)));
    }
}
