package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.Collector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectorRepository extends JpaRepository<Collector, Integer> {

    Optional<List<Collector>> findAllByVillageId(int villageId);

}
