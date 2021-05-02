package ge.alpin.javakhet.milkfactory.service.impl;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.repository.MilkScheduleRepository;
import ge.alpin.javakhet.milkfactory.service.MilkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilkScheduleServiceImpl implements MilkScheduleService {

    private final MilkScheduleRepository milkScheduleRepository;


    @Override
    @Transactional
    public MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException {
        MilkSchedule fromDb = milkScheduleRepository.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND));
        fromDb.setCalculated(milkSchedule.isCalculated());
        fromDb.setShift(milkSchedule.getShift());
        fromDb.setCountKiloGram(milkSchedule.getCountKiloGram());
        fromDb.setCountMilliLiter(milkSchedule.getCountMilliLiter());


        return fromDb;
    }

    @Override
    @Transactional
    public void create(MilkSchedule milkSchedule) {
        milkScheduleRepository.createMilkSchedule(milkSchedule.isCalculated(),milkSchedule.getCollectorId(),milkSchedule.getCountKiloGram(),milkSchedule.getCountMilliLiter(),
                milkSchedule.getDate(),milkSchedule.getFarmerId(),milkSchedule.getPrice(),milkSchedule.getShift().toString());
    }

    @Override
    @Transactional
    public void calculateMilkBeginEnd(boolean calculate, int farmerId, long start, long end) {
        milkScheduleRepository.calculateMilkBeginEnd(calculate, farmerId, start, end);
    }

    @Override
    public List<MilkSchedule> getByFarmerIdAndDate(int farmerId, long start, long end) {
        return milkScheduleRepository.getAllByFarmerIdAndDateBetween(farmerId, start, end);
    }


}
