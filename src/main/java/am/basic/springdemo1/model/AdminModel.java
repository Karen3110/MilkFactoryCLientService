package am.basic.springdemo1.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString
public class AdminModel {
    String name;
    String surname;
    String username;
    String password;
    int id;
}
