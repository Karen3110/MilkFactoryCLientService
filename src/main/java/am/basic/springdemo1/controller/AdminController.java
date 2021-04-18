package am.basic.springdemo1.controller;

import am.basic.springdemo1.model.*;
import am.basic.springdemo1.model.Dto.CountingFarmerDto;
import am.basic.springdemo1.model.Dto.SignInDto;
import am.basic.springdemo1.model.Dto.ToCountDataDto;
import am.basic.springdemo1.model.Dto.ToSaveDto;
import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.repository.*;
import am.basic.springdemo1.repository.impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import am.basic.springdemo1.service.AdminService;
import am.basic.springdemo1.service.impl.AdminServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService = new AdminServiceImpl(new AdminRepositoryImpl());

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
        VillageRepository villageRepository = new VillageRepositoryImpl();
        return ResponseEntity.ok(villageRepository.getAll());
    }

    //village select
    @PostMapping("/counting/village/{villageID}")
    public ResponseEntity getCountingCollectors(@PathVariable int villageID) {

        CollectorRepository collectorRepository = new CollectorRepositoryImpl();
        List<CollectorModel> collectorModels = collectorRepository.getCollectorByVillageID(villageID);

        return ResponseEntity.ok(collectorModels);
    }

    // collector select
    @PostMapping("/counting/collector")
    public ResponseEntity getCountingFarmers(@RequestBody CountingFarmerDto countingFarmerDto) {

        FarmerRepository farmerRepository = new FarmerRepositoryImpl();
        List<FarmerModel> farmerModels = farmerRepository.getFarmersByCollectorIDAndVillageID(countingFarmerDto.getCollectorID(), countingFarmerDto.getVillageID());

        return ResponseEntity.ok(farmerModels);
    }

    // farmer select date select and  submit
    @PostMapping("/counting/count")
    public ResponseEntity getToCountData(@RequestBody ToCountDataDto countDataDto) {

        EverydayMilkRepository everydayMilkRepository = new EverydayMilkRepositoryImpl();
        FarmerRepository farmerRepository = new FarmerRepositoryImpl();
        CollectorRepository collectorRepository = new CollectorRepositoryImpl();
        VillageRepository villageRepository = new VillageRepositoryImpl();

        List<EveryDayMilkModel> data = everydayMilkRepository.getFarmerListBeginEndDate(countDataDto.getFarmerID(), countDataDto.getStart(), countDataDto.getEnd());
        FarmerModel farmer = farmerRepository.getByID(countDataDto.getFarmerID());
        CollectorModel collector = collectorRepository.getByID(countDataDto.getCollectorID());
        VillageModel village = villageRepository.getByID(countDataDto.getVillageID());
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

        EverydayMilkRepository everydayMilkRepository = new EverydayMilkRepositoryImpl();
        everydayMilkRepository.calculateMilkBeginEnd(countDataDto.getFarmerID(), countDataDto.getStart(), countDataDto.getEnd(), true);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/counting/uncalculate")
    public ResponseEntity unCalculateData(@RequestBody ToCountDataDto countDataDto) {

        EverydayMilkRepository everydayMilkRepository = new EverydayMilkRepositoryImpl();
        everydayMilkRepository.calculateMilkBeginEnd(countDataDto.getFarmerID(), countDataDto.getStart(), countDataDto.getEnd(), false);

        return ResponseEntity.status(200).build();
    }

    // after editing data
    @PostMapping("/counting/save")
    public ResponseEntity saveEditedData(@RequestBody ToSaveDto toSaveDto) {
        EverydayMilkRepository everydayMilkRepository = new EverydayMilkRepositoryImpl();

        for (EveryDayMilkModel item : toSaveDto.getData()) {
            everydayMilkRepository.update(item);
        }
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/counting/print")
    public ResponseEntity printData() {
        return null;
    }


    @GetMapping("/update")
    public ResponseEntity getVillageFarmerCollectorData() {

        CollectorRepository collectorRepository = new CollectorRepositoryImpl();
        CollectorAccessRepository collectorAccessRepository = new CollectorAccessRepositoryImpl();
        VillageRepository villageRepository = new VillageRepositoryImpl();
        FarmerRepository farmerRepository = new FarmerRepositoryImpl();
        Map<String, Object> data = new HashMap<>();

        data.put("farmers", farmerRepository.getAll());
        data.put("collectors", collectorRepository.getAll());
        data.put("villages", villageRepository.getAll());
        data.put("collectorAccaunts", collectorAccessRepository.getAll());


        return ResponseEntity.ok(data);
    }

    @PostMapping("/update/update/farmer")
    public ResponseEntity updateFarmer(@RequestBody FarmerModel farmer) {
        FarmerRepository farmerRepository = new FarmerRepositoryImpl();
        farmerRepository.update(farmer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/village")
    public ResponseEntity updateVillage(@RequestBody VillageModel village) {
        VillageRepository villageRepository = new VillageRepositoryImpl();
        villageRepository.update(village);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/collector")
    public ResponseEntity updateVillage(@RequestBody CollectorModel collector) {
        CollectorRepository collectorRepository = new CollectorRepositoryImpl();
        collectorRepository.update(collector);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update/update/collector-account")
    public ResponseEntity updateVillage(@RequestBody CollectorAccessModel collectorAccessModel) {
        CollectorAccessRepository collectorAccessRepository = new CollectorAccessRepositoryImpl();
        collectorAccessRepository.update(collectorAccessModel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}



