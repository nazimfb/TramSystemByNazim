package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.entity.Stop;
import az.code.trammanagementsystem.entity.Waypoint;
import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.entity.RouteSchedule;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDetailDTO {
    private String name;
    private List<Stop> stops;
    private RouteSchedule schedule;
}
