package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Village;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VillageService {
    void delete(int id);

    Village update(int id, Village village) throws ResponseException;

    Page<Village> getAll(Pageable pageable);
}


