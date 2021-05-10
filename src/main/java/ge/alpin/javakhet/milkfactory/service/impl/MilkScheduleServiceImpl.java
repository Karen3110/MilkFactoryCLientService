package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.model.dto.ToCountDataDto;
import ge.alpin.javakhet.milkfactory.repository.MilkScheduleRepository;
import ge.alpin.javakhet.milkfactory.service.CollectorService;
import ge.alpin.javakhet.milkfactory.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MilkScheduleServiceImpl implements MilkScheduleService {

    @Autowired
    private MilkScheduleRepository milkScheduleRepository;

    @Autowired
    private CollectorService collectorService;


    @Override
    @Transactional

    public MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException {
        MilkSchedule fromDb = milkScheduleRepository
                .findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND,"Id not found to update milk-schedule data"));
        fromDb.setCalculated(milkSchedule.isCalculated());
        fromDb.setShift(milkSchedule.getShift());
        fromDb.setCountKiloGram(milkSchedule.getCountKiloGram());
        fromDb.setCountMilliLiter(milkSchedule.getCountMilliLiter());

        return fromDb;
    }

    @Override
    public Page<MilkSchedule> getAll(Pageable pageable) {
        return milkScheduleRepository.findAll(pageable);
    }

    @Override
    public MilkSchedule create(MilkSchedule milkSchedule) {
        return milkScheduleRepository.save(milkSchedule);
    }

    @Override
    @Transactional
    public void calculateMilkBeginEnd(boolean calculate, int farmerId, long start, long end) {
        milkScheduleRepository.calculateMilkBeginEnd(calculate, farmerId, start, end);
    }

    @Override
    public List<MilkSchedule> getByFarmerIdAndDate(int farmerId, long start, long end) throws ResponseException {
        return milkScheduleRepository
                .getAllByFarmerIdAndDateBetween(farmerId, start, end)
                .orElseThrow(()-> new ResponseException(HttpStatus.NOT_FOUND,"Farmer_id or Date not found to  get milk schedule data"));
    }

    @Override
    public Map<String, Object> getToCountData(ToCountDataDto toCountDataDto) throws ResponseException {
        Map<String, Object> data = new HashMap<>();
        List<MilkSchedule> milkList = getByFarmerIdAndDate(toCountDataDto.getFarmerId(), toCountDataDto.getStart(), toCountDataDto.getEnd());
        float sumLitre = sumMilliliter(milkList);
        float sumKg = sumKilogram(milkList);
        float amount = sumAmount(milkList);
        data.put("milkList", milkList);
        data.put("sumLitre", sumLitre);
        data.put("sumKg", sumKg);
        data.put("amount", amount);
        data.put("collectorsByDay", getCollectorsListBeginEnd(milkList));

        return data;
    }

    @Override
    public float sumMilliliter(List<MilkSchedule> data) {
        float sum = 0.0f;

        for (MilkSchedule item :
                data) {
            sum += item.getCountMilliLiter();
        }
        return sum;
    }

    @Override
    public float sumKilogram(List<MilkSchedule> data) {
        float sum = 0.0f;

        for (MilkSchedule item :
                data) {
            sum += item.getCountKiloGram();
        }
        return sum;
    }

    @Override
    public float sumAmount(List<MilkSchedule> data) {
        float sum = 0.0f;

        for (MilkSchedule item :
                data) {
            sum += item.getCountMilliLiter() * item.getPrice();
        }
        return sum;
    }

    @Override
    public List<Map<String, Object>> getCollectorsListBeginEnd(List<MilkSchedule> lst) {
        List<Map<String, Object>> data = new LinkedList<>();
        Map<String, Object> tempData;


        long start = lst.get(0).getDate();
        long end = lst.get(0).getDate();
        int collectorId = lst.get(0).getCollectorId();
        boolean changed = false;
        for (int i = 1; i < lst.size(); i++) {
            if (lst.get(i).getCollectorId() != collectorId) {
                changed = true;
            }
            if (changed) {
                tempData = new HashMap<>();
                tempData.put("collector", collectorService.getCollectorFulName(collectorId));
                tempData.put("start", start);
                tempData.put("end", end);
                data.add(tempData);
                changed = false;
                start = end = lst.get(i).getDate();
                collectorId = lst.get(i).getCollectorId();
            } else {
                end = lst.get(i).getDate();
            }

        }
        tempData = new HashMap<>();
        tempData.put("collector", collectorService.getCollectorFulName(collectorId));
        tempData.put("start", start);
        tempData.put("end", end);
        data.add(tempData);

        return data;
    }
}
