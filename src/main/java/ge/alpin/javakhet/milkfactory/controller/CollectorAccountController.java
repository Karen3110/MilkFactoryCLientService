package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.CollectorAccount;
import ge.alpin.javakhet.milkfactory.model.dto.SignInDto;
import ge.alpin.javakhet.milkfactory.service.CollectorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collector-account")
public class CollectorAccountController {

    @Autowired
    private CollectorAccountService collectorAccountService;


    @GetMapping("/list")
    public ResponseEntity<List<CollectorAccount>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(collectorAccountService.getAll(pageable));
    }
    @PostMapping("/login")
    public ResponseEntity<CollectorAccount> signIn(@RequestBody SignInDto signInDto) throws ResponseException {
        return ResponseEntity.ok(collectorAccountService.getByLoginAndPassword(signInDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        collectorAccountService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectorAccount> update(@PathVariable int id, @RequestBody CollectorAccount collectorAccount) throws ResponseException {
        return ResponseEntity.ok(collectorAccountService.update(id, collectorAccount));
    }

    @PostMapping("/add")
    public ResponseEntity<CollectorAccount> create(@RequestBody CollectorAccount collectorAccount) {
        return ResponseEntity.ok(collectorAccountService.create(collectorAccount));
    }


    @GetMapping("{id}")
    public ResponseEntity<CollectorAccount> getById(@PathVariable int id) throws ResponseException {
        return ResponseEntity.ok(collectorAccountService.getById(id));
    }



}
