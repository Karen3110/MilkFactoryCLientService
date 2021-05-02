package am.basic.springdemo.controller;

import am.basic.springdemo.repository.EverydayMilkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final EverydayMilkRepository everydayMilkRepository;


    @GetMapping("/test1")
    ResponseEntity test1() {
        return new ResponseEntity(everydayMilkRepository.getAllByFarmerIdAndDateBetween(1, System.currentTimeMillis(), System.currentTimeMillis()), HttpStatus.OK);

    }
}
