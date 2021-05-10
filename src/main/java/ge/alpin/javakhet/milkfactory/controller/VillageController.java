package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.PageResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Collector;
import ge.alpin.javakhet.milkfactory.model.Farmer;
import ge.alpin.javakhet.milkfactory.model.Village;
import ge.alpin.javakhet.milkfactory.service.CollectorService;
import ge.alpin.javakhet.milkfactory.service.FarmerService;
import ge.alpin.javakhet.milkfactory.service.VillageService;
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
    private FarmerService farmerService;

    @Autowired
    private VillageService villageService;

    @Autowired
    private CollectorService collectorService;

    @GetMapping("/list")
    public ResponseEntity<PageResponse<Village>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(villageService.getAll(pageable)));
    }

    @GetMapping("/{id}/farmers")
    public ResponseEntity<List<Farmer>> getFarmersByVillage(@PathVariable("id") int villageId) throws ResponseException {
        return ResponseEntity.ok(farmerService.getByVillageId(villageId));
    }

    @GetMapping("/id")
    public ResponseEntity<Village> getById(@PathVariable int id) throws ResponseException {
        return ResponseEntity.ok(villageService.getById(id));
    }

    @GetMapping("/{id}/collectors")
    public ResponseEntity<List<Collector>> getCollectorsByVillage(@PathVariable("id") int villageId) throws ResponseException {
        return ResponseEntity.ok(collectorService.getCollectorsByVillageId(villageId));
    }

    @PostMapping("/add")
    public ResponseEntity<Village> create(@RequestBody Village village) {
        return ResponseEntity.ok(villageService.create(village));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int villageId) {
        villageService.delete(villageId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Village> update(@PathVariable int id, @RequestBody Village village) throws ResponseException {
        return ResponseEntity.ok(villageService.update(id, village));
    }
}
