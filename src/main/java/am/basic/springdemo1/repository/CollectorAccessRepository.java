package am.basic.springdemo1.repository;


import am.basic.springdemo1.model.CollectorAccessModel;

public interface CollectorAccessRepository {

    void add(CollectorAccessModel collectorAccessModel);

    void update(CollectorAccessModel collectorAccessModel);

    void delete(int id);

    CollectorAccessModel getByID(int id);

    CollectorAccessModel getByUsernamePassword(String username, String password);
}
