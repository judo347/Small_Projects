package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.Model;

public class Controller {

    @FXML private ListView<?> listviewWeeks;
    @FXML private Button goalsAddButton;
    @FXML private Button weeksAddButton;
    @FXML private Button goalsRemoveButton;
    @FXML private ListView<?> listviewTemplates;
    @FXML private Button weeksRemoveButton;
    @FXML private TableView<?> tableGoals;

    public void initialize(Model model){
        //TODO
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
