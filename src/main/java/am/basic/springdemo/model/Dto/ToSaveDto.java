package am.basic.springdemo.model.Dto;

import am.basic.springdemo.model.MilkSchedule;
import lombok.Data;

import java.util.List;

@Data
public class ToSaveDto {
    private List<MilkSchedule> data;
}
