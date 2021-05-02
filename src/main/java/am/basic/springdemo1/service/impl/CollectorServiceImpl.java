package am.basic.springdemo1.service.impl;

import am.basic.springdemo1.model.CollectorAccess;
import am.basic.springdemo1.model.Collector;
import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.model.exception.SignedInException;
import am.basic.springdemo1.repository.CollectorAccessRepository;
import am.basic.springdemo1.repository.CollectorRepository;

import am.basic.springdemo1.service.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {
    @Autowired
    private final CollectorRepository collectorRepository;

    @Autowired
    private final CollectorAccessRepository collectorAccessRepository;


    @Override
    public Collector signIn(String login, String password) throws NotFoundException {

        CollectorAccess collectorAccess = collectorAccessRepository.getByLoginAndPassword(login, password);
        NotFoundException.check(collectorAccess == null, "Wrong username or password");
        return collectorRepository.getById(collectorAccess.getCollectorID());

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
}
