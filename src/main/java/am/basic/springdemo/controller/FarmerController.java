package am.basic.springdemo.controller;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Farmer;
import am.basic.springdemo.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping("/{id}")
    private  ResponseEntity<Farmer> update(@PathVariable int id, @RequestBody Farmer farmer) throws ResponseException {


        return ResponseEntity.ok(farmerService.update(id,farmer));
    }


}
