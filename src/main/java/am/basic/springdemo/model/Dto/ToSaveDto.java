package am.basic.springdemo.model.Dto;

import am.basic.springdemo.model.EveryDayMilk;
import lombok.Data;

import java.util.List;

@Data
public class ToSaveDto {
    private List<EveryDayMilk> data;
}
