package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

public class Controller {

    @FXML private ListView<Week> listviewWeeks;
    @FXML private Button goalsAddButton;
    @FXML private Button weeksAddButton;
    @FXML private Button goalsRemoveButton;
    @FXML private ListView<Template> listviewTemplates;
    @FXML private Button weeksRemoveButton;
    @FXML private TableView<Goal> tableGoals;

    @FXML private TableColumn<Goal, String>     goalViewDiscription;
    @FXML private TableColumn<Goal, Float>      goalViewPlannedDistance;
    @FXML private TableColumn<Goal, Integer>    goalViewPlannedDuration;
    @FXML private TableColumn<Goal, String>    goalViewPlannedDay;
    @FXML private TableColumn<Goal, Float>      goalViewCompletedDistance;
    @FXML private TableColumn<Goal, Integer>    goalViewCompletedDuration;
    @FXML private TableColumn<Goal, String>    goalViewCompletedDay;


    @FXML
    private void initialize(){
        //goalViewDiscription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
    }

    public void initialize(Model model){

        ObservableList<Week> weeks = FXCollections.observableArrayList(model.getWeeks());
        this.listviewWeeks.setItems(weeks);

        ObservableList<Template> templates = FXCollections.observableArrayList(model.getTemplates());
        this.listviewTemplates.setItems(templates);

        goalTableViewInitialize(model);
    }

    private void goalTableViewInitialize(Model model){

        //goalViewDiscription.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
        goalViewDiscription.setCellValueFactory(new PropertyValueFactory<>("description"));
        //goalViewPlannedDistance.setCellValueFactory(cellData -> cellData.getValue().plannedDistanceProperty());
        goalViewPlannedDistance.setCellValueFactory(new PropertyValueFactory<>("plannedDistance"));
        //goalViewPlannedDuration.setCellValueFactory(cellData -> cellData.getValue().getPlannedMinutes());
        goalViewPlannedDuration.setCellValueFactory(new PropertyValueFactory<>("plannedMinutes"));
        //goalViewPlannedDay.setCellValueFactory(cellData -> cellData.getValue().plannedWeekdayProperty());
        goalViewPlannedDay.setCellValueFactory(new PropertyValueFactory<>("plannedWeekday"));
        //goalViewCompletedDistance.setCellValueFactory(cellData -> cellData.getValue().getCompletedDistance());
        goalViewCompletedDistance.setCellValueFactory(new PropertyValueFactory<>("completedDistance"));
        //goalViewCompletedDuration.setCellValueFactory(cellData -> cellData.getValue().completedMinutesProperty());
        goalViewCompletedDuration.setCellValueFactory(new PropertyValueFactory<>("completedMinutes"));
        //goalViewCompletedDay.setCellValueFactory(cellData -> cellData.getValue().completedWeekdayProperty());
        goalViewCompletedDay.setCellValueFactory(new PropertyValueFactory<>("completedWeekday"));

        //Triggers when a week is selected
        this.listviewWeeks.getSelectionModel().selectedItemProperty().addListener(observable -> {

            Week selectedWeek = listviewWeeks.getSelectionModel().getSelectedItem();
            ObservableList<Goal> goals = FXCollections.observableArrayList(model.getGoalsOfWeek(selectedWeek));
            tableGoals.setItems(goals);
        });
    }

    @FXML
    void addWeek(ActionEvent event) {

    }

    @FXML
    void removeSelectedWeek(ActionEvent event) {

    }

    @FXML
    void addGoal(ActionEvent event) {

    }

    @FXML
    void RemoveSelectedGoal(ActionEvent event) {

    }

}
