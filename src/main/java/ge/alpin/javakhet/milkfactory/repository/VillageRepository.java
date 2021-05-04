package ge.alpin.javakhet.milkfactory.repository;


import ge.alpin.javakhet.milkfactory.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VillageRepository extends JpaRepository<Village, Integer> {


    Village getById(int id);


}

