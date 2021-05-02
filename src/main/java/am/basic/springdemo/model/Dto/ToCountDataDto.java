package am.basic.springdemo.model.Dto;

import lombok.Data;



@Data
public class ToCountDataDto {

   private int villageId;

   private int collectorId;

   private int farmerId;

   private long start;

   private long end;

}
