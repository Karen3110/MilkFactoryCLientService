package am.basic.springdemo.controller;

import am.basic.springdemo.commons.model.PageResponse;
import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Farmer;
import am.basic.springdemo.model.MilkSchedule;
import am.basic.springdemo.service.FarmerService;
import am.basic.springdemo.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable int id) {
        farmerService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    private ResponseEntity<Farmer> update(@PathVariable int id, @RequestBody Farmer farmer) throws ResponseException {
        return ResponseEntity.ok(farmerService.update(id, farmer));
    }


    @GetMapping("/{id}/milk-schedule")
    public ResponseEntity<List<MilkSchedule>> getByFarmerIdAndDate(@PathVariable("id") int farmerId, @RequestParam long start, @RequestParam long end) {
        return ResponseEntity.ok(milkScheduleService.getByFarmerIdAndDate(farmerId,start,end));
    }

}
