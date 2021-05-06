package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Village;
import ge.alpin.javakhet.milkfactory.repository.VillageRepository;
import ge.alpin.javakhet.milkfactory.service.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VillageServiceImpl implements VillageService {

    @Autowired
    private final VillageRepository villageRepository;

    @Override
    public void delete(int id) {
        villageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Village update(int id, Village village) throws ResponseException {
        Village fromDb = villageRepository.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND));
        fromDb.setVillageName(village.getVillageName());
        return fromDb;
    }

    @Override
    public Page<Village> getAll(Pageable pageable) {
        return villageRepository.findAll(pageable);
    }

    @Override
    public Village create(Village village) {
        return villageRepository.save(village);
    }

}
