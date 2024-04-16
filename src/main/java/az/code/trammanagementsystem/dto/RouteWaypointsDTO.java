package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.entity.Waypoint;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteWaypointsDTO {
    private long id;
    private List<WaypointDTO> waypoints;

//    public void setWaypoints(List<Waypoint> waypoints) {
//        HashMap<Double, Double> waypointsMap = new HashMap<>();
//        for (Waypoint waypoint : waypoints) {
//            waypointsMap.put(waypoint.getLat(),waypoint.getLng());
//        }
//        this.waypoints = waypointsMap;
//    }

    //    {40.6079 : 43.7081}
}
