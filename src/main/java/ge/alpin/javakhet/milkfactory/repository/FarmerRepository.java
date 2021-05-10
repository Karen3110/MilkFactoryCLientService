package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {

    Optional<List<Farmer>> getAllByCollectorId(int collectorId);

    Optional<List<Farmer>> getAllByVillageIdOrderBySurname(int villageId);

    Optional<Farmer> getFarmerByPhone(String phone);


}
