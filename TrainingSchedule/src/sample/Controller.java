package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.Model;
import model.Template;
import model.Week;

public class Controller {

    @FXML private ListView<Week> listviewWeeks;
    @FXML private Button goalsAddButton;
    @FXML private Button weeksAddButton;
    @FXML private Button goalsRemoveButton;
    @FXML private ListView<Template> listviewTemplates;
    @FXML private Button weeksRemoveButton;
    @FXML private TableView<?> tableGoals;

    public void initialize(Model model){

        ObservableList<Week> weeks = FXCollections.observableArrayList(model.getWeeks());
        this.listviewWeeks.setItems(weeks);

        ObservableList<Template> templates = FXCollections.observableArrayList(model.getTemplates());
        this.listviewTemplates.setItems(templates);
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
