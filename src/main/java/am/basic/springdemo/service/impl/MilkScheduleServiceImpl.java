package am.basic.springdemo.service.impl;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.MilkSchedule;
import am.basic.springdemo.repository.MilkScheduleRepository;
import am.basic.springdemo.service.MilkScheduleService;
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
    //todo implemented
    public MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException {
        MilkSchedule fromDb = milkScheduleRepository.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND));


        return fromDb;
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
