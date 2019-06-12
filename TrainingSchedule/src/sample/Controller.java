package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.*;
import utils.KeyEventListener;

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

        weekTableViewInitialize(model);
        goalTableViewInitialize(model);


    }

    private void weekTableViewInitialize(Model model){

        //Triggers when a week is selected
        this.listviewWeeks.getSelectionModel().selectedItemProperty().addListener(observable -> {

            if(listviewWeeks.getSelectionModel().getSelectedItem() != null){
                Week selectedWeek = listviewWeeks.getSelectionModel().getSelectedItem();
                ObservableList<Goal> goals = FXCollections.observableArrayList(model.getGoalsOfWeek(selectedWeek));
                tableGoals.setItems(goals);
            }

        });
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

        goalViewDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        goalViewPlannedDistance.setCellFactory(TextFieldTableCell.forTableColumn());
        goalViewPlannedDuration.setCellFactory(TextFieldTableCell.forTableColumn());
        goalViewPlannedDay.setCellFactory(TextFieldTableCell.forTableColumn());
        goalViewCompletedDistance.setCellFactory(TextFieldTableCell.forTableColumn());
        goalViewCompletedDuration.setCellFactory(TextFieldTableCell.forTableColumn());
        goalViewCompletedDay.setCellFactory(TextFieldTableCell.forTableColumn());

        //Triggers for completed goals
        //goalViewCompletedDistance.setCellFactory(Callback<TableCell<Goal, String>, TableCell<>>);
        //goalViewCompletedDistance.addEventHandler(new EventHandler<>());
        //goalViewCompletedDistance.
        //goalViewCompletedDuration.setCellFactory(TextFieldTableCell.forTableColumn());
        //goalViewCompletedDay.setCellFactory(TextFieldTableCell.forTableColumn());
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

    @FXML void weekNameUpClicked(ActionEvent event){
        Week selectedWeek = getSelectedWeek();

        if(selectedWeek != null){
            selectedWeek.incrementWeekNumber();
            updateWeeksList();
        }
    }

    @FXML void weekNameDownClicked(ActionEvent event){
        Week selectedWeek = getSelectedWeek();

        if(selectedWeek != null){
            selectedWeek.decrementWeekNumber();
            updateWeeksList();
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
