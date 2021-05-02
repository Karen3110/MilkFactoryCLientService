package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import ge.alpin.javakhet.milkfactory.model.lcp.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MilkScheduleRepository extends JpaRepository<MilkSchedule, Integer> {


    List<MilkSchedule> getByFarmerId(int farmerID);

    @Modifying
    @Query(value = "INSERT INTO milk_schedule VALUES(0,:calculated,:collectorId,:countKiloGram,:countMilliLiter,:date,:farmerId,:price,:shift)", nativeQuery = true)
    void createMilkSchedule(@Param("calculated") boolean calculated, @Param("collectorId") int collectorId, @Param("countKiloGram") float countKiloGram, @Param("countMilliLiter") float countMilliLiter, @Param("date") long date, @Param("farmerId") int farmerId, @Param("price") float price, @Param("shift") String shift);


    List<MilkSchedule> getAllByFarmerIdAndDateBetween(int farmerID, long dateBegin, long dateEnd);


    @Modifying
    @Query(value = "UPDATE  milks_chedule SET calculated =  :calculated where  farmer_id = :farmerId AND date BETWEEN  :start and :end ", nativeQuery = true)
    void calculateMilkBeginEnd(@Param("calculated") boolean calculated, @Param("farmerId") int farmerId, @Param("start") long dateBegin, @Param("end") long dateEnd);


}
