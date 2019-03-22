package NBPApp.Model.Table;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeRatesTable {

    private char Table;
    private String no;
    private String effectiveDate;
    private List<RatesTable> rates;

}
