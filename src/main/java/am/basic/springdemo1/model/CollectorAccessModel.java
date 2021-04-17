package am.basic.springdemo1.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CollectorAccessModel {
    int id;
    int collectorID;
    String login;
    String password;
}
