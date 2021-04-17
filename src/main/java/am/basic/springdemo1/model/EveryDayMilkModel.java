package am.basic.springdemo1.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EveryDayMilkModel {
    int id;
    String date;
    String shift;
    int farmerID;
    int collectorID;
    float countMilliLiter;
    float countKiloGram;
    float price;
    boolean calculated;
}
