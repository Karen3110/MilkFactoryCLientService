package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ConstantsRepository extends JpaRepository<Constants, Integer> {

    Constants getByName(String name);
    void deleteById(int id);

    @Modifying
    @Query(value = "INSERT INTO constants VALUES (0,:name,:value)", nativeQuery = true)
    void create(@Param("name") String name, @Param("value") String value);
}
