package am.basic.springdemo1.repository;


import am.basic.springdemo1.model.VillageModel;

import java.util.List;

public interface VillageRepository {
    void add(VillageModel collector);

    void update(VillageModel collector);

    void delete(int id);

    VillageModel getByID(int id);

    List<VillageModel> getAll();
}

