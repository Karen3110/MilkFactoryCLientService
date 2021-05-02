package am.basic.springdemo.service.impl;

import am.basic.springdemo.model.Collector;
import am.basic.springdemo.model.CollectorAccount;
import am.basic.springdemo.model.exception.NotFoundException;
import am.basic.springdemo.model.exception.SignedInException;
import am.basic.springdemo.repository.CollectorAccountRepository;
import am.basic.springdemo.repository.CollectorRepository;
import am.basic.springdemo.service.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private final CollectorRepository collectorRepository;

    @Autowired
    private final CollectorAccountRepository collectorAccountRepository;


    @Override
    public Collector signIn(String login, String password) throws NotFoundException {

        CollectorAccount collectorAccount = collectorAccountRepository.getByLoginAndPassword(login, password);
        NotFoundException.check(collectorAccount == null, "Wrong username or password");
        return collectorRepository.getById(collectorAccount.getCollectorId());

    }

    @Override
    public void checkSigned(HttpServletRequest request, HttpServletResponse response) throws SignedInException {
        String username = CookieUtil.getCookieValue("username", request);
        SignedInException.check(username == null, "please sign in first");
    }

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie("username", response);
        CookieUtil.deleteCookie("password", response);
        request.getSession().invalidate();
    }

    @Override
    public List<Collector> getByVillageId(int villageId) {
        return collectorRepository.getAllByVillageId(villageId);
    }

    @Override
    public void delete(int id) {
        collectorRepository.deleteById(id);
    }
}
