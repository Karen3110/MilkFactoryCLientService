package am.basic.springdemo1.controller;

import am.basic.springdemo1.repository.EverydayMilkRepository;
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
        return new ResponseEntity(everydayMilkRepository.findAllByFarmerIdAndDateBetween(1, "2021-01-03 00:00:00", "2021-01-05 00:00:00"), HttpStatus.OK);

    }
}