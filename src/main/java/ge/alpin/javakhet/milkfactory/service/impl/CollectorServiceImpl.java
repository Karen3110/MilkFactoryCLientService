package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Collector;
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

    @Override
    public Collector create(Collector collector) {
        return collectorRepository.save(collector);
    }

    @Override
    public Collector getById(int id) throws ResponseException {
        return collectorRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Id not found to get collector"));
    }

    @Override
    public String getCollectorFulName(int collectorId) {
        return collectorRepository.getOne(collectorId).getName()
                + " "
                + collectorRepository.getOne(collectorId).getSurname();
    }

    @Override
    public List<Collector> getCollectorsByVillageId(int id) throws ResponseException {
        return collectorRepository
                .findAllByVillageId(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Village_id not found to get collectors list"));
    }

    @Override
    @Transactional
    public Collector update(int id, Collector collector) throws ResponseException {
        Collector fromDb = collectorRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Collector_id not found to update collector's Data"));
        fromDb.setName(collector.getName());
        fromDb.setSurname(collector.getSurname());
        return fromDb;
    }

    @Override
    public void delete(int id) {
        collectorRepository.deleteById(id);
    }

    @Override
    public Page<Collector> getAll(Pageable pageable) {
        return collectorRepository.findAll(pageable);
    }

}
