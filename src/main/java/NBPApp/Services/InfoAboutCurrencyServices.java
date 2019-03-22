package NBPApp.Services;


import NBPApp.Controller.InfoAboutCurrencyController;
import NBPApp.Model.Single.ExchangeRatesSeries;
import NBPApp.Model.Single.RatesCurrency;
import NBPApp.Model.Table.ExchangeRatesTable;
import NBPApp.Model.Table.RatesTable;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InfoAboutCurrencyServices {



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
            e.printStackTrace();
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
        }else if(controller.getChoiceBoxDataType().getValue().equals("Cena zakupu i sprzedaży")){
            dataType = "c";
        }

        String currency;
        currency = controller.getChoiceBoxCurrency().getValue().toString();
        currency = currency.substring(1,4);

        String date = "";
        if(controller.getCbToday().isSelected()) date = "today";
        if(controller.getCbSelectedDay().isSelected()) date = controller.getDpSelectedDay().getValue().toString();
        if(controller.getCbFromToDay().isSelected()) date = controller.getDpFromDay().getValue().toString() + "/" + controller.getDpToDay().getValue().toString();

        String url = "http://api.nbp.pl/api/exchangerates/rates/";
        url += String.format("%s/%s/%s", dataType, currency, date);
        return url;
    }

    public String getInformationAboutSingleCurrencyOneRecord(InfoAboutCurrencyController controller){
        ExchangeRatesSeries singleCurrency = createSingleObjectFromGivenUrl(getUrlForSingleCurrency(controller));
        String data;
        data = "Tabela nr " + singleCurrency.getRates().get(0).getNo() + " z dnia " + singleCurrency.getRates().get(0).getEffectiveDate()
            + "\nNazwa waluty\tKod waluty\tKurs średni"
                +"\n" + singleCurrency.getCurrency() + "\t" + singleCurrency.getCode() + "\t" + singleCurrency.getRates().get(0).getMid();
        return data;
    }

    public String getInformationSingleCurrenyManyRecords(InfoAboutCurrencyController controller){
        ExchangeRatesSeries singleCurrency = createSingleObjectFromGivenUrl(getUrlForSingleCurrency(controller));
        String data;

        data = "Informacje dla waluty " + controller.getChoiceBoxCurrency().getValue().toString()
                + " z okresu " + controller.getDpFromDay().getValue().toString()  + " - " + controller.getDpToDay().getValue().toString() + "\n";

        for(RatesCurrency currency : singleCurrency.getRates()){
            data += "\nTabela nr " +currency.getNo() + " z dnia " + currency.getEffectiveDate()
                    + "\nKurs: " + currency.getMid();
        }

        return data;
    }


}
