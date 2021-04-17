package am.basic.springdemo1.service;

import am.basic.springdemo1.model.CollectorModel;
import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.model.exception.SignedInException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CollectorService {


    CollectorModel signIn(String login, String password) throws NotFoundException;

    void checkSigned(HttpServletRequest request, HttpServletResponse response) throws SignedInException;

    void logOut(HttpServletRequest request, HttpServletResponse response);
}
