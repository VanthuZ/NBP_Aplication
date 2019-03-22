package NBPApp.Model;

import lombok.Data;

@Data
public class Rates {
    private String no;
    private String effectiveDate;
    private float bid;
    private float ask;
    private float mid;
}
