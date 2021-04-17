package am.basic.springdemo1.model.Dto;

import am.basic.springdemo1.model.EveryDayMilkModel;
import lombok.Data;

import java.util.List;

@Data
public class ToSaveDto {
    List<EveryDayMilkModel> data;
}
