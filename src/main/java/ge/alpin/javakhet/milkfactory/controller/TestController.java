package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.repository.MilkScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final MilkScheduleRepository milkScheduleRepository;


    @GetMapping("/test1")
    ResponseEntity test1() {
        return new ResponseEntity(milkScheduleRepository.getAllByFarmerIdAndDateBetween(1, System.currentTimeMillis(), System.currentTimeMillis()), HttpStatus.OK);

    }
}
