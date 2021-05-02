package am.basic.springdemo.controller;


import am.basic.springdemo.commons.model.PageResponse;
import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Collector;
import am.basic.springdemo.model.CollectorAccount;
import am.basic.springdemo.service.CollectorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collector-account")
public class CollectorAccountController {


    @Autowired
    private CollectorAccountService collectorAccountService;


    @GetMapping("/list")
    public ResponseEntity<PageResponse<CollectorAccount>> getAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(new PageResponse<>(collectorAccountService.getAll(pageable)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  delete(@PathVariable int id){
        collectorAccountService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<CollectorAccount> update(@PathVariable int id, @RequestBody CollectorAccount collectorAccount) throws ResponseException {
        return ResponseEntity.ok(collectorAccountService.update(id,collectorAccount));
    }




}
