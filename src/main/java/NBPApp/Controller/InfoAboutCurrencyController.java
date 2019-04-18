package NBPApp.Controller;

import NBPApp.Services.InfoAboutCurrencyServices;
import NBPApp.Services.SaveToFile;
import NBPApp.Services.SendToPrinter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import lombok.Data;

import java.io.File;
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
    SaveToFile saveToFile = new SaveToFile();
    SendToPrinter sendToPrinter = new SendToPrinter();

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
        try {
            if (cbFromToDay.isSelected()) {
                infoAboutCurrencyServices.getInformationSingleCurrencyManyRecords(this);
            } else {
                infoAboutCurrencyServices.getInformationAboutSingleCurrencyOneRecord(this);
            }
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.NONE, "Brak danych dla wybranej walutu i typu danych");
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.setTitle("Informacja");
            alert.show();
        }
    }

    public void clearData() {
        tfTextInformation.getChildren().clear();
    }

    public void saveToFile() {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Plik txt (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(tfTextInformation.getScene().getWindow());

        if(file != null){
            saveToFile.saveFile(infoAboutCurrencyServices.getTextFromTextFlow(tfTextInformation), file);
        }
    }

    public void sendToPrinter() {
        sendToPrinter.print(tfTextInformation);
    }
}

