package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Collector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectorRepository extends JpaRepository<Collector, Integer> {

    Collector getById(int id);

    List<Collector> findAllByVillageId(int id);

    Collector getCollectorByNameAndSurnameAndVillageId(String name, String surname,int villageId);

    @Modifying
    @Query(value = "INSERT INTO collector VALUES (0,:name ,:surname,:villageId)", nativeQuery = true)
    void create(@Param("name") String name, @Param("surname") String surname, @Param("villageId") int villageId);
}
