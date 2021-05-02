package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.Collector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectorRepository extends JpaRepository<Collector, Integer> {

    Collector getById(int id);

    Collector getByNameAndSurname(String name, String surname);

    List<Collector> getCollectorByVillageId(int id);


}
