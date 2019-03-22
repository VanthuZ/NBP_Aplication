package NBPApp.Model.Table;

import lombok.Data;

@Data
public class RatesTable {

    private String currency;
    private String code;
    private float mid;
    private float bid;
    private float ask;
}
