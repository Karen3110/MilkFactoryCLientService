package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import ge.alpin.javakhet.milkfactory.repository.CollectorAccountRepository;
import ge.alpin.javakhet.milkfactory.service.CollectorAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectorAccountServiceImpl implements CollectorAccountService {

    @Autowired
    private CollectorAccountRepository collectorAccountRepository;

    @Override
    public void delete(int id) {
        collectorAccountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CollectorAccount update(int id, CollectorAccount collectorAccount) throws ResponseException {
        CollectorAccount fromDb = collectorAccountRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Id not found for collector-account to update data"));
        fromDb.setLogin(collectorAccount.getLogin());
        fromDb.setPassword(collectorAccount.getPassword());
        return fromDb;
    }

    @Override
    public CollectorAccount getById(int id) throws ResponseException {
        return collectorAccountRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Id not found to get collector-account"));
    }

    @Override
    public List<CollectorAccount> getAll(Pageable pageable) {
        return collectorAccountRepository.findAll();
    }

    @Override
    public CollectorAccount signIn(SignInDto signInDto) throws ResponseException {
        return collectorAccountRepository
                .getByLoginAndPassword(signInDto.getLogin(), signInDto.getPassword())
                .orElseThrow(() -> new ResponseException(HttpStatus.UNAUTHORIZED, "Wrong login or password for collector-account to SignIn"));
    }

    @Override
    public CollectorAccount create(CollectorAccount collectorAccount) {
        return collectorAccountRepository.save(collectorAccount);
    }


}
