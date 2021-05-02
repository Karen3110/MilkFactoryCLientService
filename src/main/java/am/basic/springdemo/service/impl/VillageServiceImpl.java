package am.basic.springdemo.service.impl;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Village;
import am.basic.springdemo.repository.VillageRepository;
import am.basic.springdemo.service.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class VillageServiceImpl implements VillageService {


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


}
