package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Farmer;
import ge.alpin.javakhet.milkfactory.repository.FarmerRepository;
import ge.alpin.javakhet.milkfactory.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {


    @Autowired
    private FarmerRepository farmerRepository;


    @Override
    public void delete(int id) {
        farmerRepository.deleteById(id);
    }

    @Override
    public Farmer create(Farmer farmer) {
       return farmerRepository.save(farmer);
    }

    @Override
    @Transactional
    public Farmer update(int id, Farmer farmer) throws ResponseException {
        Farmer fromDb = farmerRepository.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND));
        fromDb.setName(farmer.getName());
        fromDb.setSurname(farmer.getSurname());
        fromDb.setVillageId(farmer.getVillageId());
        fromDb.setCollectorId(farmer.getCollectorId());
        fromDb.setPhone(farmer.getPhone());

        return fromDb;
    }

    @Override
    public Page<Farmer> getAll(Pageable pageable) {
        return farmerRepository.findAll(pageable);
    }

    @Override
    public List<Farmer> getAllByCollectorId(int collectorId) {
        return farmerRepository.getAllByCollectorId(collectorId);
    }
}
