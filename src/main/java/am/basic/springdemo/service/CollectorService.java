package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Collector;
import am.basic.springdemo.model.exception.NotFoundException;
import am.basic.springdemo.model.exception.SignedInException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CollectorService {


    Collector signIn(String login, String password) throws NotFoundException;

    void checkSigned(HttpServletRequest request, HttpServletResponse response) throws SignedInException;

    void logOut(HttpServletRequest request, HttpServletResponse response);

    List<Collector> getByVillageId(int villageID);


    void delete(int id);

    Collector update(int id, Collector collector) throws ResponseException;

    Page<Collector> getAll(Pageable pageable);
}
