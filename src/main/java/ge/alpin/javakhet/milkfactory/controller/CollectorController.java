package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.PageResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Collector;
import ge.alpin.javakhet.milkfactory.model.Farmer;
import ge.alpin.javakhet.milkfactory.service.CollectorService;
import ge.alpin.javakhet.milkfactory.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collector")
public class CollectorController {

    @Autowired
    private CollectorService collectorService;

    @Autowired
    private FarmerService farmerService;

    @GetMapping("/list")
    public ResponseEntity<PageResponse<Collector>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(collectorService.getAll(pageable)));
    }

    @GetMapping("/{id}/farmers")
    public ResponseEntity<List<Farmer>> getFarmersByCollectorId(@PathVariable int id) throws ResponseException {
        return ResponseEntity.ok(farmerService.getAllByCollectorId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collector> getById(@PathVariable int id) throws ResponseException {
        return ResponseEntity.ok(collectorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Collector> create(@RequestBody Collector collector) {
        return ResponseEntity.ok(collectorService.create(collector));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collector> update(@PathVariable int id, @RequestBody Collector collector) throws ResponseException {
        return ResponseEntity.ok(collectorService.update(id, collector));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        collectorService.delete(id);
        return ResponseEntity.ok().build();
    }


}
