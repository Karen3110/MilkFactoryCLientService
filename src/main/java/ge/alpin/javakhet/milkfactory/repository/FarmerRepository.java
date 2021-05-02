package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {

    Farmer getById(int id);

    List<Farmer> getAllByVillageId(int villageId);

    List<Farmer> getAllByCollectorId(int collectorId);

    Farmer getByPhone(String phone);

    @Modifying
    @Query(value = "INSERT INTO farmer VALUES (0,:name,:surname,:phone,:villageId,:collectorId)", nativeQuery = true)
    void create(@Param("name") String name, @Param("surname") String surname,@Param("phone") String phone, @Param("villageId") int villageId, @Param("collectorId") int collectorId);

}
