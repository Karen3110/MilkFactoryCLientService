package am.basic.springdemo1.service.impl;

import am.basic.springdemo1.model.AdminModel;
import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.model.exception.SignedInException;
import am.basic.springdemo1.repository.AdminRepository;
import am.basic.springdemo1.service.AdminService;
import util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminModel signIn(String login, String password) throws NotFoundException {

        AdminModel admin = adminRepository.getByUsernamePassword(login, password);
        System.out.println(admin);
        NotFoundException.check(admin == null, "Wrong username or password");
        return admin;
    }

    @Override
    public void logOut( HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie("username",response);
        CookieUtil.deleteCookie("password",response);
        request.getSession().invalidate();
    }

    @Override
    public void checkSigned(HttpServletRequest request,HttpServletResponse response) throws SignedInException {
        String username = CookieUtil.getCookieValue("username",request);

        SignedInException.check(username==null,"please sign in first");
    }

    public void counting(HttpServletRequest request,HttpServletResponse response){


    }
}

//+995 599 911 561