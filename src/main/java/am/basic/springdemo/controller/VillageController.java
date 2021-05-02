package am.basic.springdemo.controller;

import am.basic.springdemo.commons.model.PageResponse;
import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Collector;
import am.basic.springdemo.model.Village;
import am.basic.springdemo.service.CollectorService;
import am.basic.springdemo.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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


    @GetMapping("/list")
    public ResponseEntity<PageResponse<Village>> getAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(new PageResponse<>(villageService.getAll(pageable)));
    }

    @GetMapping("/{id}/collectors")
    public ResponseEntity<List<Collector>> getByVillage(@PathVariable("id") int villageId) {
        return ResponseEntity.ok(collectorService.getByVillageId(villageId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int villageId) {
        villageService.delete(villageId);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Village> update(@PathVariable int id, @RequestBody Village village) throws ResponseException {
        villageService.update(id,village);
        return ResponseEntity.ok().build();
    }

}
