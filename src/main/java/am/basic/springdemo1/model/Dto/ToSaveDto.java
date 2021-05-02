package am.basic.springdemo1.model.Dto;

import am.basic.springdemo1.model.EveryDayMilk;
import lombok.Data;

import java.util.List;

@Data
public class ToSaveDto {
    private List<EveryDayMilk> data;
}
