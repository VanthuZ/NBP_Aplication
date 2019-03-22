package NBPApp.Controller;

import NBPApp.Services.InfoAboutCurrencyServices;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    InfoAboutCurrencyServices infoAboutCurrencyServices = new InfoAboutCurrencyServices();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVisibilityForElements();
        setOptionInChoiceBoxCurrency();
        setOptionInChoiceBoxDataType();
    }

    private void setVisibilityForElements(){
        dpSelectedDay.visibleProperty().bind(cbSelectedDay.selectedProperty());
        dpFromDay.visibleProperty().bind(cbFromToDay.selectedProperty());
        dpToDay.visibleProperty().bind(cbFromToDay.selectedProperty());
        lbFrom.visibleProperty().bind(cbFromToDay.selectedProperty());
        lbTo.visibleProperty().bind(cbFromToDay.selectedProperty());
    }

    private void setOptionInChoiceBoxCurrency(){
        choiceBoxCurrency.setItems(FXCollections.observableArrayList(infoAboutCurrencyServices.getCurenncies()));
    }

    private void setOptionInChoiceBoxDataType(){
        choiceBoxDataType.setItems(FXCollections.observableArrayList("Kurs średni", "Cena zakupu i sprzedaży"));
    }





}
