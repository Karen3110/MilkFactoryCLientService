package ge.alpin.javakhet.milkfactory.controller;


import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.dto.ToCountDataDto;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.model.lcp.Shift;
import ge.alpin.javakhet.milkfactory.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/milk-schedule")
public class MilkScheduleController {


    private final MilkScheduleService milkScheduleService;

    @PostMapping("/add")
    public ResponseEntity<MilkSchedule> addMilkSchedule(@RequestBody MilkSchedule milkSchedule) {
        milkScheduleService.create(milkSchedule);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<MilkSchedule> saveEditedData(@PathVariable int id, @RequestBody MilkSchedule milkSchedule) throws ResponseException {
        return ResponseEntity.ok(milkScheduleService.update(id, milkSchedule));
    }

    @PatchMapping("/do-calculate/{calculate}")
    public ResponseEntity<Void> calculateData(@PathVariable boolean calculate, @RequestBody ToCountDataDto countDataDto) {
        milkScheduleService.calculateMilkBeginEnd(calculate, countDataDto.getFarmerId(), countDataDto.getStart(), countDataDto.getEnd());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/shifts")
    public ResponseEntity<Shift[]> getShifts() {
        return ResponseEntity.ok(Shift.values());
    }

}
