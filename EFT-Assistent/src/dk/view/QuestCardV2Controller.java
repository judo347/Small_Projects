package dk.view;

import dk.model.quest.Quest;
import dk.model.quest.QuestObjectives;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;



public class QuestCardV2Controller {

    @FXML private Pane background_pane;
    @FXML private VBox vbox_content;

    private Quest originalQuest;
    private PrimarySceneController psc;
    private HBox parentBox;
    private Pane layoutComponent;
    private QuestState state;

    private double max_card_width;

    public void setParent(PrimarySceneController psc){
        this.psc = psc;
    }

    public void addBoxParent(HBox parentBox, Pane layoutComponent){
        this.parentBox = parentBox;
        this.layoutComponent = layoutComponent;
    }

    //TODO move initializing stuff for vbox_content to other method
    //TODO make style to a common string/attribute
    public void setValues(Quest quest, QuestState questState){
        this.max_card_width = vbox_content.getPrefWidth();
        vbox_content.prefWidthProperty().bind(((Pane)vbox_content.getParent()).widthProperty());
        vbox_content.prefHeightProperty().bind(((Pane)vbox_content.getParent()).widthProperty());
        ((Pane) vbox_content.getParent()).setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        this.state = questState;
        this.originalQuest = quest;

        //TODO set style of vbox_content
        //TODO black lines between sections

        //Title section
        HBox titelBox = new HBox();
        titelBox.setMinWidth(max_card_width);
        //titelBox.setMinWidth(Region.USE_PREF_SIZE);
        titelBox.setAlignment(Pos.CENTER);
        ImageView traderIcon = new ImageView(); //TODO Image based on trader
        Label questTitel = new Label(quest.getQuestName());
        questTitel.setStyle("-fx-font-weight: bold;");
        titelBox.getChildren().add(traderIcon);
        titelBox.getChildren().add(questTitel);
        vbox_content.getChildren().add(titelBox);

        //Maps section
        if(quest.getMaps().size() > 1){
            HBox mapsBox = new HBox();
            mapsBox.setStyle("-fx-border-insets: 5 0 0 0; -fx-border-color: black transparent transparent transparent;");
            mapsBox.setPadding(new Insets(5,5,5,5));
            //TODO content
            vbox_content.getChildren().add(mapsBox);
        }

        //Objectives section
        VBox objectives = new VBox();
        objectives.setPadding(new Insets(5,5,5,5));
        //objectives.setPrefWidth(Double.POSITIVE_INFINITY);
        //objectives.setMinWidth(Region.USE_PREF_SIZE);
        objectives.setStyle("-fx-border-insets: 5 0 0 0; -fx-border-color: black transparent transparent transparent;");
        HBox hbox_titel_obj = new HBox();
        hbox_titel_obj.setAlignment(Pos.CENTER);
        Label label_titel_obj = new Label("Objectives");
        label_titel_obj.setStyle("-fx-font-weight: bold;");
        hbox_titel_obj.getChildren().add(label_titel_obj);
        objectives.getChildren().add(hbox_titel_obj);
        for(QuestObjectives questObj : quest.getObjectives()){
            StringBuilder objText = new StringBuilder();
            objText.append(questObj.getObjective()).append('\n');
            for(int i = 0; i < questObj.getSubObjectives().size(); i++){
                objText.append(" - ").append(questObj.getSubObjectives().get(i));
                if(i != questObj.getSubObjectives().size() -1){
                    objText.append('\n');
                }
            }

            Label label_obj = new Label(objText.toString());
            label_obj.setWrapText(true);
            label_obj.setMaxWidth(max_card_width);
            label_obj.setTextAlignment(TextAlignment.JUSTIFY);
            //Text text_obj = new Text(objText.toString());
            //text_obj.setWrappingWidth(max_card_width);
            objectives.getChildren().add(label_obj);
            //objectives.getChildren().add(text_obj);
        }
        vbox_content.getChildren().add(objectives);

        //Obj requirements
        if(quest.getRequirements().size() > 0){
            VBox requirements = new VBox();
            requirements.setPadding(new Insets(5,5,5,5));
            requirements.setStyle("-fx-border-insets: 5 0 0 0; -fx-border-color: black transparent transparent transparent;");
            HBox hbox_titel_req = new HBox();
            hbox_titel_req.setAlignment(Pos.CENTER);
            Label label_titel_req = new Label("Requirements");
            label_titel_req.setStyle("-fx-font-weight: bold;");
            hbox_titel_req.getChildren().add(label_titel_req);
            requirements.getChildren().add(hbox_titel_req);
            StringBuilder reqText = new StringBuilder();
            for(int i = 0; i < quest.getRequirements().size(); i++){
                reqText.append(quest.getRequirements().get(i));
                if(i != quest.getRequirements().size() -1)
                    reqText.append('\n');
            }
            Label label_req = new Label(reqText.toString());
            requirements.getChildren().add(label_req);
            vbox_content.getChildren().add(requirements);
        }


        //TODO Quest LL and Level requirements if LOCKED
    }

    private void setBackgroundColorToMatchState(){
        background_pane.setStyle("-fx-background-color: #" + state.getColorString());
    }

    @FXML
    void cardClicked(MouseEvent event) {
        if(state == QuestState.AVAILABLE){
            state = QuestState.ACCEPTED;
            setBackgroundColorToMatchState();
        }else if(state == QuestState.ACCEPTED){
            deleteCard();
        }else if(state == QuestState.LOCKED){
            System.out.println("Clicked locked quest!"); //TODO Currently nothing happens
        }else
            throw new IllegalArgumentException("Should not happen!");
    }

    /** Removes the questCard from the program. */
    private void deleteCard(){
        psc.completeQuestCard(originalQuest);
    }
}

