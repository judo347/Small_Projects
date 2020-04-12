package dk.view;

import dk.model.MainModel;
import dk.model.MapType;
import dk.model.PlayerInfo;
import dk.model.TraderType;
import dk.model.quest.Quest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PrimarySceneController {

    @FXML private Label topbar_label_quest_completion;

    @FXML private Label label_level_player;
    @FXML private Label label_level_prapor;
    @FXML private Label label_level_therapist;
    @FXML private Label label_level_skier;
    @FXML private Label label_level_peacekeeper;
    @FXML private Label label_level_mechanic;
    @FXML private Label label_level_ragman;
    @FXML private Label label_level_jaeger;

    @FXML private VBox vbox_maincontent;

    //TODO DELETE! and dont use!
    @FXML private HBox hbox_interchange_quests;
    @FXML private HBox hbox_labs_quests;
    @FXML private HBox hbox_customs_quests;
    @FXML private HBox hbox_woods_quests;
    @FXML private HBox hbox_mixed_quests;
    @FXML private HBox hbox_reserve_quests;
    @FXML private HBox hbox_factory_quests;
    @FXML private HBox hbox_shoreline_quests;

    private MainModel mainModel;

    private boolean isInGodMode = true;
    private SortingMode currentSortingMode = SortingMode.MAP;
    private QuestCategoryManager qcm;

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
        this.qcm = new QuestCategoryManager(this, vbox_maincontent);
        this.qcm.reloadSorting(currentSortingMode);
        reloadPlayerInfoVisuals();
    }

    /** Adds the given quest card to the correct hbox based on given mapType. */
    private void addQuestCard(PaneAndController questCardAndController, MapType mapType){

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
    }

    /** Removes all quest card from the model. (Quest hbox´s)*/
    private void clearQuestCardBoxes(){
        hbox_shoreline_quests.getChildren().clear();
        hbox_labs_quests.getChildren().clear();
        hbox_mixed_quests.getChildren().clear();
        hbox_woods_quests.getChildren().clear();
        hbox_customs_quests.getChildren().clear();
        hbox_factory_quests.getChildren().clear();
        hbox_reserve_quests.getChildren().clear();
        hbox_interchange_quests.getChildren().clear();
    }

    public void completeQuestCard(Quest quest){
        mainModel.getQm().completeQuest(quest);
        reloadQuestVisuals();
    }

    /** Reloads all visuals related to quests.
     Should be called each time changes to quests is made in the model. */
    public void reloadQuestVisuals(){
        // Clears quest boxes
        qcm.clearHboxs(currentSortingMode);

        // Re-adds all active quests
        for(Quest quest : mainModel.getQm().getActiveQuests()){
            PaneAndController questCardAndController = createQuestCard(quest, QuestState.AVAILABLE, this);

            MapType mapType;

            if(quest.getMaps().size() == 1)
                mapType = quest.getMaps().get(0);
            else
                mapType = MapType.MIXED;

            qcm.addQuestCard(questCardAndController, currentSortingMode, mapType, quest.getTrader());
        }

        //Should locked quests be shown?
        if(isInGodMode){
            for(Quest quest : mainModel.getQm().getLockedQuests()){
                PaneAndController questCardAndController = createQuestCard(quest, QuestState.LOCKED, this);

                MapType mapType;

                if(quest.getMaps().size() == 1)
                    mapType = quest.getMaps().get(0);
                else
                    mapType = MapType.MIXED;

                qcm.addQuestCard(questCardAndController, currentSortingMode, mapType, quest.getTrader());
            }
        }

        // Updates quests completed label
        setQuestCompletionLabel();
    }

    /** Reloads the visuals related to PlayerInfo: player level and loyalty levels. */
    public void reloadPlayerInfoVisuals(){
        PlayerInfo playerInfo = mainModel.getPlayerInfo();
        label_level_player.setText(String.valueOf(playerInfo.getPlayerLevel()));
        label_level_therapist.setText(String.valueOf(playerInfo.getLoyaltyLevelFromTrader(TraderType.THERAPIST)));
        label_level_prapor.setText(String.valueOf(playerInfo.getLoyaltyLevelFromTrader(TraderType.PRAPOR)));
        label_level_skier.setText(String.valueOf(playerInfo.getLoyaltyLevelFromTrader(TraderType.SKIER)));
        label_level_ragman.setText(String.valueOf(playerInfo.getLoyaltyLevelFromTrader(TraderType.RAGMAN)));
        label_level_jaeger.setText(String.valueOf(playerInfo.getLoyaltyLevelFromTrader(TraderType.JAEGER)));
        label_level_mechanic.setText(String.valueOf(playerInfo.getLoyaltyLevelFromTrader(TraderType.MECHANIC)));
        label_level_peacekeeper.setText(String.valueOf(playerInfo.getLoyaltyLevelFromTrader(TraderType.PEACEKEEPER)));
    }

    /** Creates and returns a quest card based on the given Quest. */
    private PaneAndController createQuestCard(Quest quest, QuestState questState, PrimarySceneController psc){
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dk/view/QuestCard.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dk/view/QuestCardV2.fxml"));
            Pane questCard = fxmlLoader.load();
            //QuestCardController questCardController = (QuestCardController)fxmlLoader.getController();

            //questCardController.setValues(quest, questState);
            //questCardController.setParent(psc);

            QuestCardV2Controller questCardController = (QuestCardV2Controller)fxmlLoader.getController();
            questCardController.setValues(quest, questState);
            questCardController.setParent(psc);

            return new PaneAndController(questCard, questCardController);

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }

    private void changeGodmode(boolean wantedState){
        if(wantedState != isInGodMode){
            isInGodMode = wantedState;
            reloadQuestVisuals();
        }
    }

    private void changeSortingMode(SortingMode wantedMode){
        if(wantedMode != currentSortingMode){
            currentSortingMode = wantedMode;
            qcm.reloadSorting(currentSortingMode);
        }
    }

    /** Updates the quest progression label. */
    private void setQuestCompletionLabel(){
        int quests_completed_count = mainModel.getQm().getNumberOfCompletedQuests();
        int quests_total_count = mainModel.getQm().getTotalNumberOfQuests();
        String text = quests_completed_count + " / " + quests_total_count;
        topbar_label_quest_completion.setText(text);
    }

    @FXML
    void buttonAction_plus_player(MouseEvent event) {
        mainModel.incrementPlayerLevel();
        label_level_player.setText(String.valueOf(mainModel.getPlayerInfo().getPlayerLevel()));
        reloadQuestVisuals();
    }

    @FXML
    void buttonAction_plus_prapor(MouseEvent event) {
        if(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.PRAPOR) > 3)
            return;

        mainModel.incrementTraderLoyaltyLevel(TraderType.PRAPOR);
        label_level_prapor.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.PRAPOR)));
        reloadQuestVisuals();
    }

    @FXML
    void buttonAction_plus_therapist(MouseEvent event) {
        if(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.THERAPIST) > 3)
            return;

        mainModel.incrementTraderLoyaltyLevel(TraderType.THERAPIST);
        label_level_therapist.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.THERAPIST)));
        reloadQuestVisuals();
    }

    @FXML
    void buttonAction_plus_skier(MouseEvent event) {
        if(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.SKIER) > 3)
            return;

        mainModel.incrementTraderLoyaltyLevel(TraderType.SKIER);
        label_level_skier.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.SKIER)));
        reloadQuestVisuals();
    }

    @FXML
    void buttonAction_plus_peacekeeper(MouseEvent event) {
        if(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.PEACEKEEPER) > 3)
            return;

        mainModel.incrementTraderLoyaltyLevel(TraderType.PEACEKEEPER);
        label_level_peacekeeper.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.PEACEKEEPER)));
        reloadQuestVisuals();
    }

    @FXML
    void buttonAction_plus_mechanic(MouseEvent event) {
        if(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.MECHANIC) > 3)
            return;

        mainModel.incrementTraderLoyaltyLevel(TraderType.MECHANIC);
        label_level_mechanic.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.MECHANIC)));
        reloadQuestVisuals();
    }

    @FXML
    void buttonAction_plus_ragman(MouseEvent event) {
        if(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.RAGMAN) > 3)
            return;

        mainModel.incrementTraderLoyaltyLevel(TraderType.RAGMAN);
        label_level_ragman.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.RAGMAN)));
        reloadQuestVisuals();
    }

    @FXML
    void buttonAction_plus_jaeger(MouseEvent event) {
        if(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.JAEGER) > 3)
            return;

        mainModel.incrementTraderLoyaltyLevel(TraderType.JAEGER);
        label_level_jaeger.setText(String.valueOf(mainModel.getPlayerInfo().getLoyaltyLevelFromTrader(TraderType.JAEGER)));
        reloadQuestVisuals();
    }

    @FXML
    void menu_buttonAction_save_slot0(ActionEvent event) {
        mainModel.saveSlot(0);
    }

    @FXML
    void menu_buttonAction_save_slot1(ActionEvent event) {
        mainModel.saveSlot(1);
    }

    @FXML
    void menu_buttonAction_load_slot0(ActionEvent event) {
        mainModel.loadSlot(0);
    }

    @FXML
    void menu_buttonAction_load_slot1(ActionEvent event) {
        mainModel.loadSlot(1);
    }

    @FXML void menu_buttonAction_godmode_disable(ActionEvent event) {
        changeGodmode(false);
    }

    @FXML void menu_buttonAction_godmode_enable(ActionEvent event) {
        changeGodmode(true);
    }

    @FXML void menu_buttonAction_sort_map(ActionEvent event) {
        changeSortingMode(SortingMode.MAP);
    }

    @FXML void menu_buttonAction_sort_trader(ActionEvent event) {
        changeSortingMode(SortingMode.TRADER);
    }
}
