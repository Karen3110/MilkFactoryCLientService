package am.basic.springdemo.repository;


import am.basic.springdemo.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageRepository extends JpaRepository<Village, Integer> {


    Village getById(int id);



}

