package am.basic.springdemo1.repository;


import am.basic.springdemo1.model.VillageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VillageRepository extends JpaRepository<VillageModel, Integer> {


    VillageModel getById(int id);



}

