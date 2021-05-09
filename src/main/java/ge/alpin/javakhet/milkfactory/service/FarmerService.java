package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface FarmerService {

    void delete(int id);

    Farmer create(Farmer farmer);

    Farmer update(int id, Farmer farmer) throws ResponseException;

    Page<Farmer> getAll(Pageable pageable);

    List<Farmer> getAllByCollectorId(int collectorId);

    Farmer getFarmerByPhone(String phone);

    List<Farmer> getByVillageId(int villageId);

    Farmer getById(int id) throws ResponseException;

    Map<String,Object> getByIdFulInfo(int id) throws ResponseException;
}
