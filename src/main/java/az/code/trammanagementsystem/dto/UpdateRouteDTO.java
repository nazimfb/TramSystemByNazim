package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.entity.Stop;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRouteDTO {
    private String name;
    private List<Stop> stops;
}
