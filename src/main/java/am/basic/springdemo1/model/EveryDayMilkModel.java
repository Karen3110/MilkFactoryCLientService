package am.basic.springdemo1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class EveryDayMilkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long date;

    private String shift;

    private int farmerId;

    private int collectorId;

    private float countMilliLiter;

    private float countKiloGram;

    private float price;

    private boolean calculated;

}
