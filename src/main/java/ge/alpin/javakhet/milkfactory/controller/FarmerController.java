package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.PageResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.Farmer;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.model.dto.ToCountDataDto;
import ge.alpin.javakhet.milkfactory.service.FarmerService;
import ge.alpin.javakhet.milkfactory.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farmer")
public class FarmerController {

    private final FarmerService farmerService;

    private final MilkScheduleService milkScheduleService;

    @GetMapping("/list")
    public ResponseEntity<PageResponse<Farmer>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(farmerService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getById(@PathVariable int id) throws ResponseException {
        return ResponseEntity.ok(farmerService.getById(id));
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<Map<String, Object>> getByIdFulInfo(@PathVariable int id) throws ResponseException {
        return ResponseEntity.ok(farmerService.getByIdFulInfo(id));
    }

    @PostMapping("/milk-schedule")
    public  ResponseEntity<Map<String, Object>> getToCalculateData(@RequestBody ToCountDataDto toCountDataDto) throws ResponseException {

        return ResponseEntity.ok(milkScheduleService.getToCountData(toCountDataDto));
    }

    @GetMapping("/{phone}/phone")
    public ResponseEntity<Farmer> getFarmerByPhone(@PathVariable("phone") String phone) {
        return ResponseEntity.ok(farmerService.getFarmerByPhone(phone));
    }

    @PostMapping("/add")
    private ResponseEntity<Farmer> create(@RequestBody Farmer farmer) {
        farmerService.create(farmer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Farmer> update(@PathVariable int id, @RequestBody Farmer farmer) throws ResponseException {
        return ResponseEntity.ok(farmerService.update(id, farmer));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable int id) {
        farmerService.delete(id);
        return ResponseEntity.ok().build();
    }

}
