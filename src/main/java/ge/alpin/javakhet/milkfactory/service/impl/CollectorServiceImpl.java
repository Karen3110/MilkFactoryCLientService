package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Collector;
import ge.alpin.javakhet.milkfactory.repository.CollectorAccountRepository;
import ge.alpin.javakhet.milkfactory.repository.CollectorRepository;
import ge.alpin.javakhet.milkfactory.service.CollectorService;
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
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private final CollectorRepository collectorRepository;

    @Autowired
    private final CollectorAccountRepository collectorAccountRepository;


    @Override
    public List<Collector> getByVillageId(int villageId) {
        return collectorRepository.findAllByVillageId(villageId);
    }

    @Override
    public void delete(int id) {
        collectorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Collector update(int id, Collector collector) throws ResponseException {
        Collector fromDb = collectorRepository.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND));
        fromDb.setName(collector.getName());
        fromDb.setSurname(collector.getSurname());
        return fromDb;
    }

    @Override
    public Page<Collector> getAll(Pageable pageable) {
        return collectorRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void create(Collector collector) {
        collectorRepository.create(collector.getName(),collector.getSurname(),collector.getVillageId());
    }

}
