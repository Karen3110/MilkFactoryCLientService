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
            AdminModel admin = adminService.signIn(signInDto.getUsername(), signInDto.getPassword());
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

        List<CollectorModel> collectorModels = collectorRepository.getCollectorByVillageId(villageID);

        return ResponseEntity.ok(collectorModels);
    }

    // collector select
    @PostMapping("/counting/collector")
    public ResponseEntity getCountingFarmers(@RequestBody CountingFarmerDto countingFarmerDto) {

        List<FarmerModel> farmerModels = farmerRepository.getFarmersByCollectorIdAndVillageId(countingFarmerDto.getCollectorID(), countingFarmerDto.getVillageID());

        return ResponseEntity.ok(farmerModels);
    }

    // farmer select date select and  submit
    @PostMapping("/counting/count")
    public ResponseEntity getToCountData(@RequestBody ToCountDataDto countDataDto) {


        List<EveryDayMilkModel> data = everydayMilkRepository.getFarmerListBeginEndDate(countDataDto.getFarmerID(), countDataDto.getStart(), countDataDto.getEnd());
        FarmerModel farmer = farmerRepository.getById(countDataDto.getFarmerID());
        CollectorModel collector = collectorRepository.getById(countDataDto.getCollectorID());
        VillageModel village = villageRepository.getById(countDataDto.getVillageID());
        String start = countDataDto.getStart();
        String end = countDataDto.getEnd();

        Map<String, Object> respData = new HashMap<>();
        respData.put("table", data);
        respData.put("farmer", farmer);
        respData.put("collector", collector);
        respData.put("start", start);
        respData.put("end", end);
        respData.put("village", village);

        return ResponseEntity.status(500).body(respData);
    }

    @PostMapping("/counting/calculate")
    public ResponseEntity calculateData(@RequestBody ToCountDataDto countDataDto) {

        everydayMilkRepository.calculateMilkBeginEnd(countDataDto.getFarmerID(), countDataDto.getStart(), countDataDto.getEnd(), true);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/counting/uncalculate")
    public ResponseEntity unCalculateData(@RequestBody ToCountDataDto countDataDto) {

        everydayMilkRepository.calculateMilkBeginEnd(countDataDto.getFarmerID(), countDataDto.getStart(), countDataDto.getEnd(), false);

        return ResponseEntity.status(200).build();
    }

    // after editing data
    @PostMapping("/counting/save")
    public ResponseEntity saveEditedData(@RequestBody ToSaveDto toSaveDto) {

        for (EveryDayMilkModel item : toSaveDto.getData()) {
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
    public ResponseEntity updateFarmer(@RequestBody FarmerModel farmer) {
        farmerRepository.save(farmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/village")
    public ResponseEntity updateVillage(@RequestBody VillageModel village) {
        villageRepository.save(village);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/collector")
    public ResponseEntity updateVillage(@RequestBody CollectorModel collector) {
        collectorRepository.save(collector);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/collector-account")
    public ResponseEntity updateVillage(@RequestBody CollectorAccessModel collectorAccessModel) {
        collectorAccessRepository.save(collectorAccessModel);
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



