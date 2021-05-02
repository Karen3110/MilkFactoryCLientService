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
    List<EveryDayMilkModel> getAllByFarmerIdAndDateBetween(int farmerId, String dateAfter, String dateBefore);


    @Modifying
    @Query(value = "UPDATE  everyday_milk_purchase SET calculated =  ?1 where  farmer_id = ?2 AND date BETWEEN  ?3 and ?4 ", nativeQuery = true)
    void calculateMilkBeginEnd( boolean calculated, int farmerId, String dateBegin, String dateEnd);


}
