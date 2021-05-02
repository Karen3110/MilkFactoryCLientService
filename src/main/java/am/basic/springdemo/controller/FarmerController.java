package am.basic.springdemo.controller;

import am.basic.springdemo.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farmer")
public class FarmerController {


    private final FarmerService farmerService;


    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable int id){
        farmerService.delete(id);
        return ResponseEntity.ok().build();
    }



}
