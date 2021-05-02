package am.basic.springdemo.service.impl;

import am.basic.springdemo.repository.VillageRepository;
import am.basic.springdemo.service.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VillageServiceImpl implements VillageService {


    private final VillageRepository villageRepository;


    @Override
    public void delete(int id) {
        villageRepository.deleteById(id);
    }


}
