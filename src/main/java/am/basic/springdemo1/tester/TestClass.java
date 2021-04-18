package am.basic.springdemo1.tester;

import am.basic.springdemo1.model.AdminModel;

import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.repository.EverydayMilkRepository;
import am.basic.springdemo1.repository.FarmerRepository;
import am.basic.springdemo1.repository.impl.*;
import am.basic.springdemo1.service.AdminService;
import am.basic.springdemo1.service.impl.AdminServiceImpl;

public class TestClass {
    public static void main(String[] args) throws NotFoundException {
        EverydayMilkRepository everydayMilkRepository = new EverydayMilkRepositoryImpl();

        System.out.println(everydayMilkRepository.getFarmerListBeginEndDate(1,"2021-01-03 00:00:00","2021-01-05 00:00:00"));

    }
}
