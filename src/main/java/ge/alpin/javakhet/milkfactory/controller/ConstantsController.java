package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.PageResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Constants;
import ge.alpin.javakhet.milkfactory.service.ConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/constants")
public class ConstantsController {
    @Autowired
    private ConstantsService constantsService;

    @GetMapping("/list")
    public ResponseEntity<PageResponse<Constants>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(constantsService.getAll(pageable)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Constants> getByName(@PathVariable String name) throws ResponseException {
        return ResponseEntity.ok(constantsService.getByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Constants> update(@PathVariable int id, @RequestBody Constants constants) throws ResponseException {
        return ResponseEntity.ok(constantsService.update(id, constants));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        constantsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Constants> create(@RequestBody Constants constants) {
        constantsService.create(constants);
        return ResponseEntity.ok(constantsService.create(constants));
    }

}
