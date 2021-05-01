package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminModel,Integer> {


    AdminModel getById(int id);

    AdminModel getByUsernameAndPassword(String username, String password);
}
