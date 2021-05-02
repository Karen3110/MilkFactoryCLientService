package am.basic.springdemo1.repository;


import am.basic.springdemo1.model.CollectorAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorAccessRepository  extends JpaRepository<CollectorAccess,Integer> {


    CollectorAccess getById(int id);

    CollectorAccess getByLoginAndPassword(String username, String password);

 }
