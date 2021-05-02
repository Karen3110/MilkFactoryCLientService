package am.basic.springdemo.controller;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Collector;
import am.basic.springdemo.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collector")
public class CollectorController {


    @Autowired
    private CollectorService collectorService;


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
