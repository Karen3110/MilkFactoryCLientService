package am.basic.springdemo1.repository;


import am.basic.springdemo1.model.CollectorAccessModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectorAccessRepository  extends JpaRepository<CollectorAccessModel,Integer> {


    CollectorAccessModel getById(int id);

    CollectorAccessModel getByLoginAndPassword(String username, String password);

 }
