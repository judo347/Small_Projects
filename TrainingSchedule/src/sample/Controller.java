package sample;

import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Goal;
import model.Model;
import model.Template;
import model.Week;

public class Controller {
    @FXML
    private ListView<Week> listviewWeeks;
    @FXML
    private Button goalsAddButton;
    @FXML
    private Button weeksAddButton;
    @FXML
    private Button goalsRemoveButton;
    @FXML
    private ListView<Template> listviewTemplates;
    @FXML
    private Button weeksRemoveButton;
    @FXML
    private TableView<Goal> tableGoals;
    @FXML
    private TableColumn<Goal, String> goalViewDescription;
    @FXML
    private TableColumn<Goal, String> goalViewPlannedDistance;
    @FXML
    private TableColumn<Goal, String> goalViewPlannedDuration;
    @FXML
    private TableColumn<Goal, String> goalViewPlannedDay;
    @FXML
    private TableColumn<Goal, String> goalViewCompletedDistance;
    @FXML
    private TableColumn<Goal, String> goalViewCompletedDuration;
    @FXML
    private TableColumn<Goal, String> goalViewCompletedDay;
    private Model model;

    public Controller() {
    }

    public void initialize(Model model) {
        this.model = model;
        this.updateWeeksList();
        this.updateTemplatesList();
        this.weekTableViewInitialize(model);
        this.goalTableViewInitialize(model);
    }

    private void weekTableViewInitialize(Model model) {
        this.listviewWeeks.getSelectionModel().selectedItemProperty();
        //this.addListener(model); //TODO
    }

    private void updateWeeksList() {
        ObservableList<Week> weeks = FXCollections.observableArrayList(this.model.getWeeks());
        this.listviewWeeks.setItems(weeks);
        this.updateGoalView();
    }

    private void updateGoalView() {
        Week selectedWeek = this.getSelectedWeek();
        if (selectedWeek == null) {
            this.tableGoals.setItems(FXCollections.observableList(new ArrayList()));
        } else {
            this.tableGoals.setItems(FXCollections.observableList(this.model.getGoalsOfWeek(selectedWeek)));
        }

    }

    private void updateTemplatesList() {
        ObservableList<Template> templates = FXCollections.observableArrayList(this.model.getTemplates());
        this.listviewTemplates.setItems(templates);
    }

    private void goalTableViewInitialize(Model param1) {
        // $FF: Couldn't be decompiled
    }

    @FXML
    void addWeek(ActionEvent event) {
        Calendar currentDate = Calendar.getInstance();
        Week newWeek = new Week(currentDate);
        this.model.getWeeks().add(newWeek);
        this.updateWeeksList();
    }

    @FXML
    void removeSelectedWeek(ActionEvent event) {
        Week selectedWeek = this.getSelectedWeek();
        if (selectedWeek != null) {
            this.model.removeWeek(selectedWeek);
            this.updateWeeksList();
        }

    }

    @FXML
    void addGoal(ActionEvent event) {
        Week selectedWeek = this.getSelectedWeek();
        if (selectedWeek != null) {
            Goal newGoal = new Goal();
            newGoal.setDescription("New goal");
            selectedWeek.addGoal(newGoal);
            this.updateGoalView();
        }

    }

    @FXML
    void RemoveSelectedGoal(ActionEvent event) {
        Week selectedWeek = this.getSelectedWeek();
        Goal selectedGoal = this.getSelectedGoal();
        if (selectedGoal != null && selectedWeek != null) {
            selectedWeek.removeGoal(selectedGoal);
            this.updateGoalView();
            this.tableGoals.getSelectionModel().select(this.tableGoals.getItems().size() - 1);
        }

    }

    @FXML
    void weekNameUpClicked(ActionEvent event) {
        Week selectedWeek = this.getSelectedWeek();
        if (selectedWeek != null) {
            selectedWeek.incrementWeekNumber();
            this.updateWeeksList();
        }

    }

    @FXML
    void weekNameDownClicked(ActionEvent event) {
        Week selectedWeek = this.getSelectedWeek();
        if (selectedWeek != null) {
            selectedWeek.decrementWeekNumber();
            this.updateWeeksList();
        }

    }

    private Week getSelectedWeek() {
        return (Week)this.listviewWeeks.getSelectionModel().getSelectedItem();
    }

    private Goal getSelectedGoal() {
        return (Goal)this.tableGoals.getSelectionModel().getSelectedItem();
    }

    public Model getModel() {
        return this.model;
    }
}