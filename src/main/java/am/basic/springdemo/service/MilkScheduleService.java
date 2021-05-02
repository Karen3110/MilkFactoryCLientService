package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.MilkSchedule;

import java.util.List;

public interface MilkScheduleService {

    MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException;

    void calculateMilkBeginEnd(boolean calcualte, int farmerId, long start, long end);


    List<MilkSchedule> getByFarmerIdAndDate(int farmerId, long start, long end);
}
