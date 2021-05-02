package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CollectorAccountService {

    void delete(int id);

    CollectorAccount update(int id, CollectorAccount collectorAccount) throws ResponseException;

    Page<CollectorAccount> getAll(Pageable pageable);

    CollectorAccount getByUserNameAndPassword(SignInDto signInDto);
}
