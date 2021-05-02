package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {


    Admin getById(int id);

    Admin getByUsernameAndPassword(String username, String password);
}
