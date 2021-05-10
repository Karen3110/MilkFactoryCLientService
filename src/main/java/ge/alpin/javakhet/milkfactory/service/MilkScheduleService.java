package ge.alpin.javakhet.milkfactory.service;

import ge.alpin.javakhet.milkfactory.commons.model.ResponseException;
import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.model.dto.ToCountDataDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MilkScheduleService {

    MilkSchedule update(int id, MilkSchedule milkSchedule) throws ResponseException;

    Page<MilkSchedule> getAll(Pageable pageable);

    MilkSchedule create(MilkSchedule milkSchedule);

    void calculateMilkBeginEnd(boolean calculate, int farmerId, long start, long end);

    List<MilkSchedule> getByFarmerIdAndDate(int farmerId, long start, long end);

    Map<String, Object> getToCountData(ToCountDataDto toCountDataDto) throws ResponseException;


    float sumMilliliter(List<MilkSchedule> data);

    float sumKilogram(List<MilkSchedule> data);

    float sumAmount(List<MilkSchedule> data);

    List<Map<String, Object>> getCollectorsListBeginEnd(List<MilkSchedule> data) throws ResponseException;
}
