package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Collector;
import ge.alpin.javakhet.milkfactory.model.exception.NotFoundException;
import ge.alpin.javakhet.milkfactory.model.exception.SignedInException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CollectorService {

    List<Collector> getByVillageId(int villageID);


    void delete(int id);

    Collector update(int id, Collector collector) throws ResponseException;

    Page<Collector> getAll(Pageable pageable);
}
