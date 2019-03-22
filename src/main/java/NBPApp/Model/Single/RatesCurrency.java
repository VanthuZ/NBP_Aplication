package NBPApp.Model.Single;

import lombok.Data;

@Data
public class RatesCurrency {
    private String no;
    private String effectiveDate;
    private float bid;
    private float ask;
    private float mid;
}
