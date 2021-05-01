package am.basic.springdemo1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class VillageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String villageName;

}
