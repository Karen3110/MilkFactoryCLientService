package am.basic.springdemo1.model.Dto;

import lombok.Data;



@Data
public class ToCountDataDto {
    int villageID;
    int collectorID;
    int farmerID;
    String start;
    String end;

}
