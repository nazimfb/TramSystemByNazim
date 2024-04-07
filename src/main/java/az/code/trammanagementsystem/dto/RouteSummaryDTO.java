package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.entity.RouteSchedule;
import az.code.trammanagementsystem.entity.Stop;
import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.entity.Waypoint;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteSummaryDTO {
    private long id;
    private String name;
    private RouteSchedule schedule;
}
