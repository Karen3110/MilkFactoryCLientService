package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Collector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectorService {

    List<Collector> getByVillageId(int villageID);


    void delete(int id);

    Collector update(int id, Collector collector) throws ResponseException;

    Page<Collector> getAll(Pageable pageable);

    Collector create(Collector collector) ;
}
