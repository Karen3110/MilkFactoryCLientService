package ge.alpin.javakhet.milkfactory.repository;

import ge.alpin.javakhet.milkfactory.model.MilkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MilkScheduleRepository extends JpaRepository<MilkSchedule, Integer> {

    Optional<List<MilkSchedule>> getAllByFarmerIdAndDateBetween(int farmerID, long dateBegin, long dateEnd);

    @Modifying
    @Query(value = "UPDATE  milk_schedule SET calculated =  :calculated where  farmer_id = :farmerId AND date BETWEEN  :start AND :end ", nativeQuery = true)
    void calculateMilkBeginEnd(@Param("calculated") boolean calculated, @Param("farmerId") int farmerId, @Param("start") long dateBegin, @Param("end") long dateEnd);


}
