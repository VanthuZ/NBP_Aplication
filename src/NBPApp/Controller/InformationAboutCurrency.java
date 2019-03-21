package NBPApp.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class InformationAboutCurrency {

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
}
