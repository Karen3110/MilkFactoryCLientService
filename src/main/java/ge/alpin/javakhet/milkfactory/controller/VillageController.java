package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.PageResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Farmer;
import ge.alpin.javakhet.milkfactory.model.Village;
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


    @GetMapping("/list")
    public ResponseEntity<PageResponse<Village>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(villageService.getAll(pageable)));
    }

    @GetMapping("/{id}/farmers")
    public ResponseEntity<List<Farmer>> getFarmersByVillage(@PathVariable("id") int villageId) {
        return ResponseEntity.ok(farmerService.getByVillageId(villageId));
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Village village) {
        villageService.create(village);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Village> update(@PathVariable int id, @RequestBody Village village) throws ResponseException {
        villageService.update(id, village);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int villageId) {
        villageService.delete(villageId);
        return ResponseEntity.ok().build();
    }
}
