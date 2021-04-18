package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.CollectorModel;

import java.util.List;

public interface CollectorRepository {
    void add(CollectorModel collector);

    void update(CollectorModel collector);

    void delete(int id);

    CollectorModel getByID(int id);

    CollectorModel getByNameSurname(String name, String surname);

    List<CollectorModel> getAll();
    List<CollectorModel> getCollectorByVillageID(int id);



}
