package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Constants;
import ge.alpin.javakhet.milkfactory.repository.ConstantsRepository;
import ge.alpin.javakhet.milkfactory.service.ConstantsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ConstantsServiceImpl implements ConstantsService {

    @Autowired
    private final ConstantsRepository constantsRepository;

    @Override
    public List<Constants> getAll() {
        return constantsRepository.findAll();
    }

    @Override
    public Page<Constants> getAll(Pageable pageable) {
        return constantsRepository.findAll(pageable);
    }

    @Override
    public Constants getByName(String name) throws ResponseException {
        return constantsRepository
                .getByName(name)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Constant name not found to get constant's object"));
    }

    @Override
    public Constants getById(int id) throws ResponseException {
        return constantsRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Id not found to get constant-object"));
    }

    @Override
    public Constants create(Constants constants) {
        return constantsRepository.save(constants);
    }

    @Override
    @Transactional
    public Constants update(int id, Constants constants) throws ResponseException {
        Constants fromDb = constantsRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Id not found to update constant's data"));
        fromDb.setName(constants.getName());
        fromDb.setValue(constants.getValue());
        return fromDb;
    }

    @Override
    public void delete(int id) {
        constantsRepository.deleteById(id);
    }
}
