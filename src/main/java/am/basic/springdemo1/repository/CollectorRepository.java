package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.CollectorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectorRepository extends JpaRepository<CollectorModel, Integer> {

    CollectorModel getById(int id);

    CollectorModel getByNameAndSurname(String name, String surname);

    List<CollectorModel> getCollectorByVillageId(int id);


}
