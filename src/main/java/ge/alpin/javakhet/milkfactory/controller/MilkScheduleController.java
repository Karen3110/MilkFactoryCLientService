package ge.alpin.javakhet.milkfactory.controller;

import ge.alpin.javakhet.milkfactory.commons.model.PageResponse;
import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.model.dto.ToCountDataDto;
import ge.alpin.javakhet.milkfactory.model.lcp.Shift;
import ge.alpin.javakhet.milkfactory.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/milk-schedule")
public class MilkScheduleController {
    private final MilkScheduleService milkScheduleService;

    @GetMapping("/list")
    public ResponseEntity<PageResponse<MilkSchedule>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(milkScheduleService.getAll(pageable)));
    }


    @GetMapping("/shifts")
    public ResponseEntity<Shift[]> getShifts() {
        return ResponseEntity.ok(Shift.values());
    }

    @PostMapping("/add")
    public ResponseEntity<MilkSchedule> addMilkSchedule(@RequestBody MilkSchedule milkSchedule) {
        milkScheduleService.create(milkSchedule);
        return ResponseEntity.ok().build();
    }

    //  edited row on table
    @PutMapping("/{id}")
    public ResponseEntity<MilkSchedule> saveEditedData(@PathVariable int id, @RequestBody MilkSchedule milkSchedule) throws ResponseException {
        return ResponseEntity.ok(milkScheduleService.update(id, milkSchedule));
    }

    @PatchMapping("/do-calculate/{calculate}")
    public ResponseEntity<Void> calculateData(@PathVariable boolean calculate, @RequestBody ToCountDataDto countDataDto) {
        milkScheduleService.calculateMilkBeginEnd(calculate, countDataDto.getFarmerId(), countDataDto.getStart(), countDataDto.getEnd());
        return ResponseEntity.ok().build();
    }


}
