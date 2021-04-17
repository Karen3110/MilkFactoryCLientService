package am.basic.springdemo1.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FarmerModel {
    int id;
    String name;
    String surname;
    int villageID;
    int collectorID;
}
