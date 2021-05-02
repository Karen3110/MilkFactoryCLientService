package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FarmerService {

    void delete(int id);


    Farmer update(int id, Farmer farmer) throws ResponseException;

    Page<Farmer> getAll(Pageable pageable);


    List<Farmer> getAllByCollectorId(int collectorId);
}
