package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {

    List<Farmer> getAllByCollectorId(int collectorId);

    List<Farmer> getAllByVillageIdOrderBySurname(int villageId);

    Farmer getFarmerByPhone(String phone);


}
