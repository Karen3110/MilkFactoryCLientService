package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.PageResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.model.dto.ToCountDataDto;
import ge.alpin.javakhet.milkfactory.model.lcp.Shift;
import ge.alpin.javakhet.milkfactory.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/milk-schedule")
public class MilkScheduleController {
    @Autowired
    private MilkScheduleService milkScheduleService;

    @GetMapping("/list")
    public ResponseEntity<PageResponse<MilkSchedule>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(milkScheduleService.getAll(pageable)));
    }

    @GetMapping("/shifts")
    public ResponseEntity<Shift[]> getShifts() {
        return ResponseEntity.ok(Shift.values());
    }

    @PostMapping
    public ResponseEntity<MilkSchedule> create(@RequestBody MilkSchedule milkSchedule) {
        return ResponseEntity.ok(milkScheduleService.create(milkSchedule));
    }

    //  edited row on table
    @PutMapping("/{id}")
    public ResponseEntity<MilkSchedule> update(@PathVariable int id, @RequestBody MilkSchedule milkSchedule) throws ResponseException {
        return ResponseEntity.ok(milkScheduleService.update(id, milkSchedule));
    }

    @PatchMapping("/do-calculate/{calculate}")
    public ResponseEntity<Void> calculate(@PathVariable boolean calculate, @RequestBody ToCountDataDto countDataDto) {
        milkScheduleService.calculateMilkBeginEnd(calculate, countDataDto.getFarmerId(), countDataDto.getStart(), countDataDto.getEnd());
        return ResponseEntity.ok().build();
    }

}
