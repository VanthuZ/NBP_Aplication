package NBPApp.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoAboutCurrencyController implements Initializable {

    @FXML
    ChoiceBox choiceBoxCurrency;
    @FXML
    ChoiceBox choiceBoxDataType;
    @FXML
    CheckBox cbToday;
    @FXML
    CheckBox cbSelectedDay;
    @FXML
    CheckBox cbFromToDay;
    @FXML
    DatePicker dpSelectedDay;
    @FXML
    DatePicker dpFromDay;
    @FXML
    DatePicker dpToDay;
    @FXML
    Label lbFrom;
    @FXML
    Label lbTo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dpSelectedDay.visibleProperty().bind(cbSelectedDay.selectedProperty());
        dpFromDay.visibleProperty().bind(cbFromToDay.selectedProperty());
        dpToDay.visibleProperty().bind(cbFromToDay.selectedProperty());
        lbFrom.visibleProperty().bind(cbFromToDay.selectedProperty());
        lbTo.visibleProperty().bind(cbFromToDay.selectedProperty());

    }
}
