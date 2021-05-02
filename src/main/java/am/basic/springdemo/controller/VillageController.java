package am.basic.springdemo.controller;

import am.basic.springdemo.model.Collector;
import am.basic.springdemo.service.CollectorService;
import am.basic.springdemo.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/village")
public class VillageController {


    @Autowired
    private CollectorService collectorService;

    @Autowired
    private VillageService villageService;

    @GetMapping("/{id}/collectors")
    public ResponseEntity<List<Collector>> getByVillage(@PathVariable("id") int villageId) {
        return ResponseEntity.ok(collectorService.getByVillageId(villageId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int villageId) {
        villageService.delete(villageId);
        return ResponseEntity.ok().build();
    }

}
