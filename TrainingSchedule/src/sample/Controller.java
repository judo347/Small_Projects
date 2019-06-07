package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.ArrayList;
import java.util.Calendar;

public class Controller {

    @FXML private ListView<Week> listviewWeeks;
    @FXML private Button goalsAddButton;
    @FXML private Button weeksAddButton;
    @FXML private Button goalsRemoveButton;
    @FXML private ListView<Template> listviewTemplates;
    @FXML private Button weeksRemoveButton;
    @FXML private TableView<Goal> tableGoals;

    @FXML private TableColumn<Goal, String> goalViewDescription;
    @FXML private TableColumn<Goal, String>      goalViewPlannedDistance;
    @FXML private TableColumn<Goal, String>    goalViewPlannedDuration;
    @FXML private TableColumn<Goal, String>     goalViewPlannedDay;
    @FXML private TableColumn<Goal, String>      goalViewCompletedDistance;
    @FXML private TableColumn<Goal, String>    goalViewCompletedDuration;
    @FXML private TableColumn<Goal, String>     goalViewCompletedDay;

    private Model model;

    public void initialize(Model model){

        this.model = model;

        updateWeeksList();

        updateTemplatesList();

        goalTableViewInitialize(model);
    }

    private void updateWeeksList(){
        ObservableList<Week> weeks = FXCollections.observableArrayList(model.getWeeks());
        this.listviewWeeks.setItems(weeks);
        updateGoalView();
    }

    private void updateGoalView(){

        Week selectedWeek = getSelectedWeek();

        if(selectedWeek == null){
            tableGoals.setItems(FXCollections.observableList(new ArrayList<>()));
        }else
            tableGoals.setItems(FXCollections.observableList(model.getGoalsOfWeek(selectedWeek)));
    }

    private void updateTemplatesList(){
        ObservableList<Template> templates = FXCollections.observableArrayList(model.getTemplates());
        this.listviewTemplates.setItems(templates);
    }

    private void goalTableViewInitialize(Model model){

        //Empty table text
        this.tableGoals.setPlaceholder(new Label("No goals has been created for the selected week."));

        goalViewDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        goalViewPlannedDistance.setCellValueFactory(cellData -> cellData.getValue().plannedDistanceProperty());
        goalViewPlannedDuration.setCellValueFactory(cellData -> cellData.getValue().plannedMinutesProperty());
        goalViewPlannedDay.setCellValueFactory(cellData -> cellData.getValue().plannedWeekdayProperty());
        goalViewCompletedDistance.setCellValueFactory(cellData -> cellData.getValue().completedDistanceProperty());
        goalViewCompletedDuration.setCellValueFactory(cellData -> cellData.getValue().completedMinutesProperty());
        goalViewCompletedDay.setCellValueFactory(cellData -> cellData.getValue().completedWeekdayProperty());

        //Triggers when a week is selected
        this.listviewWeeks.getSelectionModel().selectedItemProperty().addListener(observable -> {

            if(listviewWeeks.getSelectionModel().getSelectedItem() != null){
                Week selectedWeek = listviewWeeks.getSelectionModel().getSelectedItem();
                ObservableList<Goal> goals = FXCollections.observableArrayList(model.getGoalsOfWeek(selectedWeek));
                tableGoals.setItems(goals);
            }

        });
    }

    @FXML
    void addWeek(ActionEvent event) {

        Calendar currentDate = Calendar.getInstance();
        Week newWeek = new Week(currentDate);

        model.getWeeks().add(newWeek);

        updateWeeksList();
    }

    @FXML
    void removeSelectedWeek(ActionEvent event) {

        Week selectedWeek = getSelectedWeek();

        if(selectedWeek != null){
            model.removeWeek(selectedWeek);
            updateWeeksList();
        }
    }

    @FXML
    void addGoal(ActionEvent event) {

        Week selectedWeek = getSelectedWeek();

        if(selectedWeek != null){

            Goal newGoal = new Goal();
            newGoal.setDescription("New goal");
            selectedWeek.addGoal(newGoal);
            updateGoalView();
        }
    }

    @FXML
    void RemoveSelectedGoal(ActionEvent event) {

        Week selectedWeek = getSelectedWeek();
        Goal selectedGoal = getSelectedGoal();

        if(selectedGoal != null && selectedWeek != null){

            selectedWeek.removeGoal(selectedGoal);
            updateGoalView();
            tableGoals.getSelectionModel().select(tableGoals.getItems().size()-1);
        }
    }

    private Week getSelectedWeek(){
        return listviewWeeks.getSelectionModel().getSelectedItem();
    }

    private Goal getSelectedGoal(){
        return tableGoals.getSelectionModel().getSelectedItem();
    }

    public Model getModel() {
        return model;
    }
}
