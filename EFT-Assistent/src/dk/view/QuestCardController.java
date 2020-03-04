package dk.view;

import dk.model.MapType;
import dk.model.Quest;
import dk.model.QuestObjectives;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class QuestCardController {
    @FXML
    private Label label_traderName;

    @FXML
    private Label label_questName;

    @FXML
    private Label label_reqLevel;

    @FXML
    private VBox vbox_objectives;

    @FXML
    private VBox vbox_requirements;

    @FXML
    private VBox vbox_maps;

    public Quest originalQuest;

    ArrayList<Label> objectiveLabels = new ArrayList<>();
    ArrayList<Label> requirementLabels = new ArrayList<>();

    public void setValues(Quest quest){
        originalQuest = quest;
        label_traderName.setText(quest.getTrader().getName());
        label_questName.setText(quest.getQuestName());
        label_reqLevel.setText(String.valueOf(quest.getRequiredLevel()));


        for(MapType type : quest.getMaps())
            vbox_maps.getChildren().add(new Label("  " + type.getName()));

        for(String string : quest.getRequirements()){
            Label label = new Label("  " + string);
            requirementLabels.add(label);
            vbox_requirements.getChildren().add(label);
        }

        System.out.println("Debug");

        //TODO Objectives
        for(QuestObjectives qo : quest.getObjectives()){
            Label obj_label = new Label("  " + qo.getObjective());
            vbox_objectives.getChildren().add(obj_label);
            for(String subObj : qo.getSubObjectives()){
                Label subObj_label = new Label("     " + subObj);
                vbox_objectives.getChildren().add(subObj_label);
            }
        }
    }
}
