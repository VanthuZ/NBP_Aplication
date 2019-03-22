package NBPApp.Model;


import lombok.Data;

import java.util.List;

@Data
public class ExchangeRatesSeries {

    private char Table;
    private String Currency;
    private String Code;
    private List<Rates> rates;
}
