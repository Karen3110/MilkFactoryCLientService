package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Farmer;

public interface FarmerService {

    void delete(int id);


    Farmer update(int id, Farmer farmer) throws ResponseException;
}
