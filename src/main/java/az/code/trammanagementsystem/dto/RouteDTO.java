package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.entity.Waypoint;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class RouteDTO {
    private String name;
    private List<Waypoint> waypoints;
}
