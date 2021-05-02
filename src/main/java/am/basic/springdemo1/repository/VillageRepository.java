package am.basic.springdemo1.repository;


import am.basic.springdemo1.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageRepository extends JpaRepository<Village, Integer> {

    Village getById(int id);



}

