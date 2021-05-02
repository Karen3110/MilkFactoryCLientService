package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.CollectorAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CollectorAccountService {

    void delete(int id);

    CollectorAccount update(int id, CollectorAccount collectorAccount) throws ResponseException;

    Page<CollectorAccount> getAll(Pageable pageable);

}
