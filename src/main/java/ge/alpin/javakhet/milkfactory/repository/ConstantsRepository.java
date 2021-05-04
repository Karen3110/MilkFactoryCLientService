package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Constants;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConstantsRepository extends JpaRepository<Constants, Integer> {

    Constants getByName(String name);
    void deleteById(int id);


}
