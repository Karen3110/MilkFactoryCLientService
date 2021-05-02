package am.basic.springdemo1.controller;

import am.basic.springdemo1.model.*;
import am.basic.springdemo1.model.Dto.CountingFarmerDto;
import am.basic.springdemo1.model.Dto.SignInDto;
import am.basic.springdemo1.model.Dto.ToCountDataDto;
import am.basic.springdemo1.model.Dto.ToSaveDto;
import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.repository.*;
import am.basic.springdemo1.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {


    private final VillageRepository villageRepository;

    private final FarmerRepository farmerRepository;

    private final EverydayMilkRepository everydayMilkRepository;

    private final CollectorRepository collectorRepository;

    private final AdminService adminService;

    private final CollectorAccessRepository collectorAccessRepository;

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody SignInDto signInDto) {
        try {
            Admin admin = adminService.signIn(signInDto.getUsername(), signInDto.getPassword());
            System.out.println(admin);
            return new ResponseEntity(admin, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (RuntimeException throwable) {
            return ResponseEntity.status(HttpStatus.OK).body("Internal server error");
        }
    }

    @PostMapping("/logOut")
    public ResponseEntity logOut() {
        //
        return null;
    }

    @GetMapping("/counting")
    public ResponseEntity getCountingVillages() {
        return ResponseEntity.ok(villageRepository.findAll());
    }

    //village select
    @PostMapping("/counting/village/{villageID}")
    public ResponseEntity getCountingCollectors(@PathVariable int villageID) {

        List<Collector> collectors = collectorRepository.getCollectorByVillageId(villageID);

        return ResponseEntity.ok(collectors);
    }

    // collector select
    @PostMapping("/counting/collector")
    public ResponseEntity getCountingFarmers(@RequestBody CountingFarmerDto countingFarmerDto) {

        List<Farmer> farmers = farmerRepository.getFarmersByCollectorIdAndVillageId(countingFarmerDto.getCollectorId(), countingFarmerDto.getVillageId());

        return ResponseEntity.ok(farmers);
    }

    // farmer select date select and  submit
    @PostMapping("/counting/count")
    public ResponseEntity getToCountData(@RequestBody ToCountDataDto countDataDto) {


        List<EveryDayMilk> data = everydayMilkRepository.getAllByFarmerIdAndDateBetween(countDataDto.getFarmerId(), countDataDto.getStart(), countDataDto.getEnd());
        Farmer farmer = farmerRepository.getById(countDataDto.getFarmerId());
        Collector collector = collectorRepository.getById(countDataDto.getCollectorId());
        Village village = villageRepository.getById(countDataDto.getVillageId());
        Map<String, Object> respData = new HashMap<>();
        respData.put("table", data);
        respData.put("farmer", farmer);
        respData.put("collector", collector);
        respData.put("start", countDataDto.getStart());
        respData.put("end", countDataDto.getEnd());
        respData.put("village", village);

        return ResponseEntity.status(500).body(respData);
    }

    @PostMapping("/counting/calculate")
    public ResponseEntity calculateData(@RequestBody ToCountDataDto countDataDto) {

        everydayMilkRepository.calculateMilkBeginEnd(true, countDataDto.getFarmerId(), countDataDto.getStart(), countDataDto.getEnd());

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/counting/uncalculate")
    public ResponseEntity unCalculateData(@RequestBody ToCountDataDto countDataDto) {

        everydayMilkRepository.calculateMilkBeginEnd(false, countDataDto.getFarmerId(), countDataDto.getStart(), countDataDto.getEnd());

        return ResponseEntity.status(200).build();
    }

    // after editing data
    @PostMapping("/counting/save")
    public ResponseEntity saveEditedData(@RequestBody ToSaveDto toSaveDto) {

        for (EveryDayMilk item : toSaveDto.getData()) {
            everydayMilkRepository.save(item);
        }
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/counting/print")
    public ResponseEntity printData() {
        return null;
    }


    @GetMapping("/update")
    public ResponseEntity getVillageFarmerCollectorData() {

        Map<String, Object> data = new HashMap<>();

        data.put("farmers", farmerRepository.findAll());
        data.put("collectors", collectorRepository.findAll());
        data.put("villages", villageRepository.findAll());
        data.put("collectorAccaunts", collectorAccessRepository.findAll());


        return ResponseEntity.ok(data);
    }

    @PostMapping("/update/update/farmer")
    public ResponseEntity updateFarmer(@RequestBody Farmer farmer) {
        farmerRepository.save(farmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/village")
    public ResponseEntity updateVillage(@RequestBody Village village) {
        villageRepository.save(village);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/collector")
    public ResponseEntity updateVillage(@RequestBody Collector collector) {
        collectorRepository.save(collector);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/collector-account")
    public ResponseEntity updateVillage(@RequestBody CollectorAccess collectorAccess) {
        collectorAccessRepository.save(collectorAccess);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/delete/farmer/{farmerID}")
    public ResponseEntity deleteFarmer(@PathVariable int farmerID) {
        farmerRepository.deleteById(farmerID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/delete/village/{villageID}")
    public ResponseEntity deleteVillage(@PathVariable int villageID) {
        villageRepository.deleteById(villageID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/delete/collector/{collectorID}")
    public ResponseEntity deleteCollector(@PathVariable int collectorID) {
        collectorRepository.deleteById(collectorID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/delete/collector-account/{accountID}")
    public ResponseEntity deleteCollectorAccount(@PathVariable int accountID) {
        collectorAccessRepository.deleteById(accountID);
        return ResponseEntity.ok().build();
    }


}



