package am.basic.springdemo1.service.impl;

import am.basic.springdemo1.model.CollectorAccessModel;
import am.basic.springdemo1.model.CollectorModel;
import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.model.exception.SignedInException;
import am.basic.springdemo1.repository.CollectorRepository;
import am.basic.springdemo1.repository.impl.CollectorAccessRepositoryImpl;
import am.basic.springdemo1.service.CollectorService;
import util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CollectorServiceImpl implements CollectorService {
    private final CollectorRepository collectorRepository;

    public CollectorServiceImpl(CollectorRepository collectorRepository) {
        this.collectorRepository = collectorRepository;
    }


    @Override
    public CollectorModel signIn(String login, String password) throws NotFoundException {

        CollectorAccessModel collectorAccessModel = new CollectorAccessRepositoryImpl().getByUsernamePassword(login, password);

        NotFoundException.check(collectorAccessModel == null, "Wrong username or password");

        return collectorRepository.getByID(collectorAccessModel.getCollectorID());

    }

    @Override
    public void checkSigned(HttpServletRequest request, HttpServletResponse response) throws SignedInException {
        String username = CookieUtil.getCookieValue("username",request);

        SignedInException.check(username==null,"please sign in first");
    }

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie("username",response);
        CookieUtil.deleteCookie("password",response);
        request.getSession().invalidate();
    }
}
