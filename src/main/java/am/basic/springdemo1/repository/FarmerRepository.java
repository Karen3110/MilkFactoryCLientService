package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.FarmerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerRepository extends JpaRepository<FarmerModel,Integer> {

    FarmerModel getById(int id);

    FarmerModel getByNameAndSurnameAndVillageId(String name, String surname, int villageID);

    List<FarmerModel> getByVillageId(int villageID);

    List<FarmerModel> getFarmersByCollectorId(int collectorID);

    List<FarmerModel> getFarmersByCollectorIdAndVillageId(int collectorID, int villageID);
}
