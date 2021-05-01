package am.basic.springdemo1.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class EveryDayMilkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String date;
    String shift;
    int farmerId;
    int collectorId;
    float countMilliLiter;
    float countKiloGram;
    float price;
    boolean calculated;
}
