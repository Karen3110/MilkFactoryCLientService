package am.basic.springdemo.service.impl;

import am.basic.springdemo.model.Admin;
import am.basic.springdemo.model.exception.NotFoundException;
import am.basic.springdemo.model.exception.SignedInException;
import am.basic.springdemo.repository.AdminRepository;
import am.basic.springdemo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;


    @Override
    public Admin signIn(String login, String password) throws NotFoundException {

        Admin admin = adminRepository.getByUsernameAndPassword(login, password);
        System.out.println(admin);
        NotFoundException.check(admin == null, "Wrong username or password");
        return admin;
    }

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie("username", response);
        CookieUtil.deleteCookie("password", response);
        request.getSession().invalidate();
    }

    @Override
    public void checkSigned(HttpServletRequest request, HttpServletResponse response) throws SignedInException {
        String username = CookieUtil.getCookieValue("username", request);

        SignedInException.check(username == null, "please sign in first");
    }

    public void counting(HttpServletRequest request, HttpServletResponse response) {


    }
}

//+995 599 911 561
