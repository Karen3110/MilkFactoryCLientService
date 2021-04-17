package am.basic.springdemo1.controller;

import am.basic.springdemo1.model.*;
import am.basic.springdemo1.model.Dto.CountingFarmerDto;
import am.basic.springdemo1.model.Dto.SignInDto;
import am.basic.springdemo1.model.Dto.ToCountDataDto;
import am.basic.springdemo1.model.Dto.ToSaveDto;
import am.basic.springdemo1.model.exception.NotFoundException;
import am.basic.springdemo1.repository.CollectorRepository;
import am.basic.springdemo1.repository.EverydayMilkRepository;
import am.basic.springdemo1.repository.FarmerRepository;
import am.basic.springdemo1.repository.VillageRepository;
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
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (RuntimeException throwable) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/logOut")
    public ResponseEntity logOut(@RequestBody SignInDto signInDto) {
        //
        return null;
    }

    @GetMapping("/counting")
    public ResponseEntity getCountingVillages() {
        VillageRepository villageRepository = new VillageRepositoryImpl();
        return ResponseEntity.status(500).body(villageRepository.getVillageAll());
    }

    //village select
    @PostMapping("/counting/village")
    public ResponseEntity getCountingCollectors(@RequestBody int id) {

        CollectorRepository collectorRepository = new CollectorRepositoryImpl();
        List<CollectorModel> collectorModels = collectorRepository.getCollectorByVillageID(id);
        return ResponseEntity.status(500).body(collectorModels);
    }

    // collector select
    @PostMapping("/counting/collector")
    public ResponseEntity getCountingFarmers(@RequestBody CountingFarmerDto countingFarmerDto) {

        FarmerRepository farmerRepository = new FarmerRepositoryImpl();

        List<FarmerModel> farmerModels = farmerRepository.getFarmersByCollectorIDAndVillageID(countingFarmerDto.getCollectorID(), countingFarmerDto.getVillageID());
        return ResponseEntity.status(500).body(farmerModels);
    }

    // farmer select date select and  submit
    @PostMapping("/counting/count")
    public ResponseEntity getToCountData(@RequestBody ToCountDataDto countDataDto) {

        EverydayMilkRepository everydayMilkRepository = new EverydayMilkRepositoryImpl();
        FarmerRepository farmerRepository = new FarmerRepositoryImpl();
        CollectorRepository collectorRepository = new CollectorRepositoryImpl();

        List<EveryDayMilkModel> data = everydayMilkRepository.getFarmerListBeginEndDate(countDataDto.getFarmerID(), countDataDto.getStart(), countDataDto.getEnd());
        FarmerModel farmer = farmerRepository.getByID(countDataDto.getFarmerID());
        CollectorModel collector = collectorRepository.getByID(countDataDto.getCollectorID());
        String start = countDataDto.getStart();
        String end = countDataDto.getEnd();

        Map<String, Object> respData = new HashMap<>();
        respData.put("table", data);
        respData.put("farmer", farmer);
        respData.put("collector", collector);
        respData.put("start", start);
        respData.put("end", end);


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
}



