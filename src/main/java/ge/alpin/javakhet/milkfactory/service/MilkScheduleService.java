package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;

import java.util.List;

public interface MilkScheduleService {

    MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException;

    void calculateMilkBeginEnd(boolean calcualte, int farmerId, long start, long end);


    List<MilkSchedule> getByFarmerIdAndDate(int farmerId, long start, long end);
}
