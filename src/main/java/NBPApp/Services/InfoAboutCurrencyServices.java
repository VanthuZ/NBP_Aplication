package NBPApp.Services;


import NBPApp.Controller.InfoAboutCurrencyController;
import NBPApp.Model.Single.ExchangeRatesSeries;
import NBPApp.Model.Single.RatesCurrency;
import NBPApp.Model.Table.ExchangeRatesTable;
import NBPApp.Model.Table.RatesTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;


public class InfoAboutCurrencyServices {

    Text header;
    Text nameCurrency;
    Text avgRate;
    Text askRate;
    Text bidRate;


    public ExchangeRatesTable[] createTableObjectFromGivenUrl(String givenUrl){
        URL jsonUrl;
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRatesTable[] exchangeRateTable;
        try {
            jsonUrl = new URL(givenUrl);
            exchangeRateTable = objectMapper.readValue(jsonUrl, ExchangeRatesTable[].class);
            return exchangeRateTable;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ExchangeRatesSeries createSingleObjectFromGivenUrl(String givenUrl){
        URL jsonUrl;
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRatesSeries ExchangeRatesSeries;
        try {
            jsonUrl = new URL(givenUrl);
            ExchangeRatesSeries = objectMapper.readValue(jsonUrl, ExchangeRatesSeries.class);
            return ExchangeRatesSeries;
        } catch (IOException e) {
            return null;
        }
    }

    public List<String> getCurenncies(){
        ExchangeRatesTable[] exchangeRatesTable;
        exchangeRatesTable = createTableObjectFromGivenUrl("http://api.nbp.pl/api/exchangerates/tables/a");
        List<String> curennciesList = new ArrayList<>();

        for(RatesTable rate : exchangeRatesTable[0].getRates()){
            curennciesList.add(
                    String.format("[%s] - %s", rate.getCode(), rate.getCurrency()));
        }
        return curennciesList;
    }

    private String getUrlForSingleCurrency(InfoAboutCurrencyController controller){

        String dataType = "";
        if(controller.getChoiceBoxDataType().getValue().equals("Kurs średni")){
            dataType = "a";
        }else if(controller.getChoiceBoxDataType().getValue().equals("Cena zakupu i sprzedaży")) {
            dataType = "c";
        }

        String currency;
        currency = controller.getChoiceBoxCurrency().getValue().toString();
        currency = currency.substring(1,4);

        String date = "";
        LocalDate today = LocalDate.now();
        if(controller.getCbToday().isSelected()){
            if(today.getDayOfWeek().toString().equals("SATURDAY") || today.getDayOfWeek().toString().equals("SUNDAY")){
                LocalDate previousFriday = today.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY ));
                date = previousFriday.toString();
            }else{
                date = "today";
            }
        }
        if(controller.getCbSelectedDay().isSelected()) {
            checkWeekendSelected(controller);
            date = controller.getDpSelectedDay().getValue().toString();
        }
        if(controller.getCbFromToDay().isSelected()) date = controller.getDpFromDay().getValue().toString() + "/" + controller.getDpToDay().getValue().toString();

        String url = "http://api.nbp.pl/api/exchangerates/rates/";
        url += String.format("%s/%s/%s", dataType, currency, date);
        return url;
    }

    private void checkWeekendSelected(InfoAboutCurrencyController controller){
        String tmpSelectedDayDate;
        tmpSelectedDayDate = controller.getDpSelectedDay().getValue().toString();
        if(LocalDate.parse(tmpSelectedDayDate).getDayOfWeek().toString().equals("SATURDAY")
                || LocalDate.parse(tmpSelectedDayDate).getDayOfWeek().toString().equals("SUNDAY")){
            Alert alert = new Alert(Alert.AlertType.NONE, "Tabele nie są generowane w weeekend! \nWybierz dzień w tygodniu.");
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.show();
        }
    }


    public void setTextsProperties(){
        header = new Text();
        header.setStyle("-fx-font-weight: bold;");
        header.setFill(Color.RED);

        nameCurrency = new Text();
        nameCurrency.setStyle("-fx-font-weight: bold;");
        nameCurrency.setFill(Color.GREEN);

        avgRate = new Text();
        avgRate.setStyle("-fx-font-weight: bold;");
        avgRate.setFill(Color.GREEN);

        bidRate = new Text();
        bidRate.setStyle("-fx-font-weight: bold;");
        bidRate.setFill(Color.GREEN);

        askRate = new Text();
        askRate.setStyle("-fx-font-weight: bold;");
        askRate.setFill(Color.GREEN);
    }


    public void getInformationAboutSingleCurrencyOneRecord(InfoAboutCurrencyController controller){
        ExchangeRatesSeries singleCurrency = createSingleObjectFromGivenUrl(getUrlForSingleCurrency(controller));
        setTextsProperties();

        header.setText("\n\n Tabela nr " + singleCurrency.getRates().get(0).getNo() + " z dnia " + singleCurrency.getRates().get(0).getEffectiveDate());
        nameCurrency.setText("\n\n Nazwa waluty\n" + " " + singleCurrency.getCurrency());

        controller.getTfTextInformation().getChildren().add(header);
        controller.getTfTextInformation().getChildren().add(nameCurrency);

        if(controller.getChoiceBoxDataType().getValue().equals("Kurs średni")){
            avgRate.setText("\n\n Kurs średni\n" + " " + singleCurrency.getRates().get(0).getMid());
            controller.getTfTextInformation().getChildren().add(avgRate);
        }else{
            bidRate.setText("\n\n Cena kupna\n" + " " + singleCurrency.getRates().get(0).getBid());
            askRate.setText("\n\n Cena sprzdaży\n" + " " + singleCurrency.getRates().get(0).getAsk());
            controller.getTfTextInformation().getChildren().add(bidRate);
            controller.getTfTextInformation().getChildren().add(askRate);
        }
    }

    public void getInformationSingleCurrencyManyRecords(InfoAboutCurrencyController controller){
        ExchangeRatesSeries singleCurrency = createSingleObjectFromGivenUrl(getUrlForSingleCurrency(controller));
        String tmpAvgRate ="";
        setTextsProperties();

        header.setText("\n\n Informacje dla waluty " + controller.getChoiceBoxCurrency().getValue().toString()
                + "\n z okresu " + controller.getDpFromDay().getValue().toString()  + " - " + controller.getDpToDay().getValue().toString() + "\n");
        controller.getTfTextInformation().getChildren().add(header);

        for(RatesCurrency currency : singleCurrency.getRates()){
            tmpAvgRate += "\n Tabela nr " +currency.getNo() + " z dnia " + currency.getEffectiveDate()
                    + "\n Kurs: " + " " +  currency.getMid() + "\n";
        }
        avgRate.setText(tmpAvgRate);
        controller.getTfTextInformation().getChildren().add(avgRate);
    }




}
