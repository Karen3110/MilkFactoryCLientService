package am.basic.springdemo.controller;

import am.basic.springdemo.commons.model.PageResponse;
import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Collector;
import am.basic.springdemo.model.Farmer;
import am.basic.springdemo.service.CollectorService;
import am.basic.springdemo.service.FarmerService;
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
    public ResponseEntity<PageResponse<Collector>> getAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(new PageResponse<>(collectorService.getAll(pageable)));
    }


    @GetMapping("/{id}/farmers")
    public ResponseEntity<List<Farmer>>  getByCollectorId(@PathVariable int id){
        return ResponseEntity.ok(farmerService.getAllByCollectorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        collectorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collector> update(@PathVariable int id, @RequestBody Collector collector) throws ResponseException {
        return ResponseEntity.ok(collectorService.update(id, collector));
    }


}
