package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MilkScheduleService {

    MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException;


    @Transactional
    void create(MilkSchedule milkSchedule);

    void calculateMilkBeginEnd(boolean calcualte, int farmerId, long start, long end);


    List<MilkSchedule> getByFarmerIdAndDate(int farmerId, long start, long end);
}
