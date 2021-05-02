package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer,Integer> {

    Farmer getById(int id);

    Farmer getByNameAndSurnameAndVillageId(String name, String surname, int villageID);

    List<Farmer> getByVillageId(int villageID);

    List<Farmer> getFarmersByCollectorId(int collectorID);

    List<Farmer> getFarmersByCollectorIdAndVillageId(int collectorID, int villageID);

    List<Farmer> getAllByCollectorId(int collectorId);

}
