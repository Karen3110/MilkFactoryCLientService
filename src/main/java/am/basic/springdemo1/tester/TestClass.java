package am.basic.springdemo1.tester;

import am.basic.springdemo1.model.AdminModel;

import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.repository.impl.*;
import am.basic.springdemo1.service.AdminService;
import am.basic.springdemo1.service.impl.AdminServiceImpl;

public class TestClass {
    public static void main(String[] args) throws NotFoundException {
        AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl());
        AdminModel admin = adminService.signIn("karen", "123123");

    }
}
