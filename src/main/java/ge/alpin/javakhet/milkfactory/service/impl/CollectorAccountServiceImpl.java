package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import ge.alpin.javakhet.milkfactory.repository.CollectorAccountRepository;
import ge.alpin.javakhet.milkfactory.service.CollectorAccountService;
import ge.alpin.javakhet.milkfactory.service.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CollectorAccountServiceImpl implements CollectorAccountService {


    private final CollectorAccountRepository collectorAccountRepository;

    private final CollectorService collectorService;

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
    public CollectorAccount getById(int id) throws ResponseException {
        return collectorAccountRepository.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<CollectorAccount> getAll(Pageable pageable) {
        return collectorAccountRepository.findAll();
    }

    @Override
    public CollectorAccount getByLoginAndPassword(SignInDto signInDto) throws ResponseException {
        return collectorAccountRepository.getByLoginAndPassword(signInDto.getLogin(), signInDto.getPassword()).orElseThrow(() -> new ResponseException(HttpStatus.UNAUTHORIZED, "Wrong login or password"));
    }

    @Override
    public CollectorAccount create(CollectorAccount collectorAccount) {
        return collectorAccountRepository.save(collectorAccount);
    }

    @Override
    public List<Map<String, Object>> getAllWithFulInfo(List<CollectorAccount> collectorAccounts) {
        List<Map<String, Object>> data = new LinkedList<>();
        collectorAccounts.forEach(item -> {
            data.add(getFulInfo(item));
        });
        return data;
    }

    @Override
    public Map<String, Object> getFulInfo(CollectorAccount collectorAccount) {
        Map<String, Object> collectorData = new HashMap<>();
        int id = collectorAccount.getCollectorId();
        String collectorFulName = "";
        try {
            collectorFulName = collectorService.getCollectorFulName(collectorService.getById(id));
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        collectorData.put("collectorAccount", collectorAccount);
        collectorData.put("fulName", collectorFulName);
        return collectorData;
    }
}
