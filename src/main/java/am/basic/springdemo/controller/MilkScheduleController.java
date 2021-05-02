package am.basic.springdemo.controller;


import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Dto.ToCountDataDto;
import am.basic.springdemo.model.MilkSchedule;
import am.basic.springdemo.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/milk-schedule")
public class MilkScheduleController {


    private final MilkScheduleService milkScheduleService;

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
