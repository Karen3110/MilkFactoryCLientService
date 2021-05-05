package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MilkScheduleService {

    MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException;

    Page<MilkSchedule> getAll(Pageable pageable);

    MilkSchedule create(MilkSchedule milkSchedule);

    void calculateMilkBeginEnd(boolean calculate, int farmerId, long start, long end);


    List<MilkSchedule> getByFarmerIdAndDate(int farmerId, long start, long end);
}
