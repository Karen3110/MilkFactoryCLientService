package am.basic.springdemo.service.impl;

import am.basic.springdemo.repository.FarmerRepository;
import am.basic.springdemo.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {


    @Autowired
    private FarmerRepository farmerRepository;


    @Override
    public void delete(int id) {
        farmerRepository.deleteById(id);
    }
}
