package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FarmerService {

    void delete(int id);

    Farmer create(Farmer farmer);

    Farmer update(int id, Farmer farmer) throws ResponseException;

    Page<Farmer> getAll(Pageable pageable);

    List<Farmer> getAllByCollectorId(int collectorId) throws ResponseException;

    Farmer getFarmerByPhone(String phone) throws ResponseException;

    List<Farmer> getByVillageId(int villageId) throws ResponseException;

    Farmer getById(int id) throws ResponseException;

}
