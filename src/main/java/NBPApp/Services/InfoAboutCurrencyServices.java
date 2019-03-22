package NBPApp.Services;


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
}
