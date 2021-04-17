package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.AdminModel;

public interface AdminRepository {

    void add(AdminModel adminModel);

    void update(AdminModel adminModel);

    void delete(int id);

    AdminModel getByID(int id);

    AdminModel getByUsernamePassword(String username, String password);
}
