package am.basic.springdemo1.repository;

import am.basic.springdemo1.model.EveryDayMilkModel;

import java.util.List;

public interface EverydayMilkRepository {

    void add(EveryDayMilkModel bill);

    void update(EveryDayMilkModel bill);

    void delete(int id);

    List<EveryDayMilkModel> getByFarmerIDAllTime(int farmerID);

    // need to write implementation of code
    //how to get between date from sql with begin end string
    //YYYY-MM-DD hh:mm:ss
    List<EveryDayMilkModel> getFarmerListBeginEndDate(int farmerID, String dateBegin, String dateEnd);

    void calculateMilkBeginEnd(int farmerID, String dateBegin, String dateEnd,boolean flag);


}
