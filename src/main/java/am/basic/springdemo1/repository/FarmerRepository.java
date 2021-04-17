package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.FarmerModel;

import java.util.List;

public interface FarmerRepository {

    void add(FarmerModel collector);

    void update(FarmerModel farmerModel);

    void delete(int id);

    FarmerModel getByID(int id);

    FarmerModel getByNameSurname(String name, String surname, int villageID);

    List<FarmerModel> getFarmersByVillageID(int villageID);

    List<FarmerModel> getFarmersByCollectorID(int collectorID);

    List<FarmerModel> getFarmersByCollectorIDAndVillageID(int collectorID,int villageID);
}
