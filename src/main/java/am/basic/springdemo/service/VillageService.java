package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Village;

public interface VillageService {
    void delete(int id);

    Village update(int id, Village village) throws ResponseException;
}
