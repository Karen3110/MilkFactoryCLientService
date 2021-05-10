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
        Farmer fromDb = farmerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "id not found to update farmer's data"));
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
    public List<Farmer> getAllByCollectorId(int collectorId) throws ResponseException {
        return farmerRepository
                .getAllByCollectorId(collectorId)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Collector_id not found to get collector's farmers"));
    }

    @Override
    public Farmer getFarmerByPhone(String phone) throws ResponseException {
        return farmerRepository
                .getFarmerByPhone(phone)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Phone Number not found to get farmer"));
    }

    @Override
    public List<Farmer> getByVillageId(int villageId) throws ResponseException {
        return farmerRepository
                .getAllByVillageIdOrderBySurname(villageId)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Village_id not found to get village's farmers"));
    }

    @Override
    public Farmer getById(int id) throws ResponseException {
        return farmerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Farmer_id not found to get farmer"));
    }


}
