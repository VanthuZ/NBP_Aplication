package NBPApp.Controller;

import NBPApp.Services.InfoAboutCurrencyServices;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import lombok.Data;
import java.net.URL;
import java.util.ResourceBundle;

@Data
public class InfoAboutCurrencyController implements Initializable {

    @FXML
    TextArea taResults;
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
    @FXML
    Button btGenerateData;
    @FXML
    Button btClearData;
    @FXML
    TextFlow tfTextInformation;

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

    public void generateData() {
        if(cbFromToDay.isSelected()){
           infoAboutCurrencyServices.getInformationSingleCurrencyManyRecords(this);
        }else{
           infoAboutCurrencyServices.getInformationAboutSingleCurrencyOneRecord(this);
        }
    }

    public void clearData() {
        tfTextInformation.getChildren().clear();
    }
}
