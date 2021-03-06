package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConstantsService {

    List<Constants> getAll();

    Page<Constants> getAll(Pageable pageable);

    Constants getById(int id) throws ResponseException;

    Constants getByName(String name) throws ResponseException;

    Constants create(Constants constants);

    Constants update(int id, Constants constants) throws ResponseException;

    void delete(int id);
}
