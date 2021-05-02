package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import ge.alpin.javakhet.milkfactory.repository.CollectorAccountRepository;
import ge.alpin.javakhet.milkfactory.service.CollectorAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CollectorAccountServiceImpl implements CollectorAccountService {


    private final CollectorAccountRepository collectorAccountRepository;


    @Override
    public void delete(int id) {
        collectorAccountRepository.deleteById(id);
    }


    @Override
    @Transactional
    public CollectorAccount update(int id, CollectorAccount collectorAccount) throws ResponseException {
        CollectorAccount fromDb = collectorAccountRepository.findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND));
        fromDb.setLogin(collectorAccount.getLogin());
        fromDb.setPassword(collectorAccount.getPassword());
        return fromDb;
    }

    @Override
    public Page<CollectorAccount> getAll(Pageable pageable) {
        return collectorAccountRepository.findAll(pageable);
    }

    @Override
    public CollectorAccount getByUserNameAndPassword(SignInDto signInDto) {
        return collectorAccountRepository.findAllByLoginAndPassword(signInDto.getLogin(),signInDto.getPassword());
    }
}
