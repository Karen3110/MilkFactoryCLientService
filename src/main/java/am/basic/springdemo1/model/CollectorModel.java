package am.basic.springdemo1.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CollectorModel {
    int id;
    String name;
    String surname;
    int villageID;
}
// getter setters are generated automaticly