package am.basic.springdemo1.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class AdminModel {
    String name;
    String surname;
    String username;
    String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
