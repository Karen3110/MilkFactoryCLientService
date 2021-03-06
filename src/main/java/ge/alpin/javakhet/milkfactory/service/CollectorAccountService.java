package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectorAccountService {

    void delete(int id);

    CollectorAccount update(int id, CollectorAccount collectorAccount) throws ResponseException;

    CollectorAccount getById(int id) throws ResponseException;

    List<CollectorAccount> getAll(Pageable pageable);

    CollectorAccount signIn(SignInDto signInDto) throws ResponseException;

    CollectorAccount create(CollectorAccount collectorAccount);

}
