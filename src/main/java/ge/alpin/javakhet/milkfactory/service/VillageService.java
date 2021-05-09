package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Village;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VillageService {
    void delete(int id);

    Village update(int id, Village village) throws ResponseException;

    Page<Village> getAll(Pageable pageable);

    Village create(Village village);

    Village getById(int id) throws ResponseException;

}


