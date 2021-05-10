package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Collector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CollectorService {

    void delete(int id);

    Collector update(int id, Collector collector) throws ResponseException;

    Page<Collector> getAll(Pageable pageable);

    Collector create(Collector collector);

    Collector getById(int id) throws ResponseException;


    String getCollectorFulName(int collectorId);

    List<Collector> getCollectorsByVillageId(int id) throws ResponseException;
}
