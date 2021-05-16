package ge.alpin.javakhet.milkfactory.model;

import ge.alpin.javakhet.milkfactory.model.lcp.Shift;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MilkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private long date;

    @Enumerated(value = EnumType.STRING)
    private Shift shift;

    private int farmerId;

    private int collectorId;

    private float countMilliliter;

    private float countKilogram;

    private float price;

    private boolean calculated;

}
