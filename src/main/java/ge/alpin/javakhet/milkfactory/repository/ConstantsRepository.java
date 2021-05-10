package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Constants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstantsRepository extends JpaRepository<Constants, Integer> {

    Optional<Constants> getByName(String name);

}
