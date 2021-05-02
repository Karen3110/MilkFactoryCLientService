package am.basic.springdemo.repository;

import am.basic.springdemo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {


    Admin getById(int id);

    Admin getByUsernameAndPassword(String username, String password);
}
