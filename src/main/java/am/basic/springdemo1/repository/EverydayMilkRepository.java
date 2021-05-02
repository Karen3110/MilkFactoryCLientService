package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.EveryDayMilkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EverydayMilkRepository extends JpaRepository<EveryDayMilkModel, Integer> {


    List<EveryDayMilkModel> getByFarmerId(int farmerID);

    // need to write implementation of code
    //how to get between date from sql with begin end string
    //YYYY-MM-DD hh:mm:ss
    List<EveryDayMilkModel> getAllByFarmerIdAndDateBetween(int farmerID, long dateBegin, long dateEnd);


    @Modifying
    @Query(value = "UPDATE  everyday_milk_purchase SET calculated =  :calculated where  farmer_id = :farmerId AND date BETWEEN  :start and :end ", nativeQuery = true)
    void calculateMilkBeginEnd( @Param("calculated") boolean calculated,@Param("farmerId") int farmerId, @Param("start") long dateBegin,@Param("end") long dateEnd);


}
