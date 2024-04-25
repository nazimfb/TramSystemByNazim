package az.code.trammanagementsystem.services.helpers;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.TramTrajectory;
import az.code.trammanagementsystem.entity.Waypoint;

import java.util.ArrayList;
import java.util.List;

import static az.code.trammanagementsystem.services.helpers.DistanceHelper.calculateDistance;

public class TrajectoryHelper {
    public static List<TramTrajectory> getTramTrajectories(Route route) {
        List<TramTrajectory> tramTrajectories = new ArrayList<>();
        if (route != null && route.getWaypoints() != null && !route.getWaypoints().isEmpty()) {
            List<Waypoint> waypoints = route.getWaypoints();

            for (int i = 0; i < waypoints.size(); i++) {
                if (waypoints.get(i).getLat() != null && waypoints.get(i).getLng() != null) {
                    Waypoint currentWaypoint = waypoints.get(i);
                    double currentLat = currentWaypoint.getLat();
                    double currentLng = currentWaypoint.getLng();

                    TramTrajectory tramTrajectory = new TramTrajectory();
                    tramTrajectory.setLatitude(currentLat);
                    tramTrajectory.setLongitude(currentLng);
                    tramTrajectory.setRoute(route);
                    tramTrajectories.add(tramTrajectory);

                    for (int j = i + 1; j < waypoints.size(); j++) {
                        Waypoint nextWaypoint = waypoints.get(j);
                        double nextLat = nextWaypoint.getLat();
                        double nextLng = nextWaypoint.getLng();

                        double distance = calculateDistance(currentLat, currentLng, nextLat, nextLng);

                        // if distance is greater than 20metres then add tram trajectory points
                        while (distance > 10) {
                            double fraction = 10 / distance;
                            double newLat = currentLat + (nextLat - currentLat) * fraction;
                            double newLng = currentLng + (nextLng - currentLng) * fraction;

                            TramTrajectory newTrajectory = new TramTrajectory();
                            newTrajectory.setLatitude(newLat);
                            newTrajectory.setLongitude(newLng);
                            newTrajectory.setRoute(route);
                            tramTrajectories.add(newTrajectory);

                            currentLat = newLat;
                            currentLng = newLng;

                            distance = calculateDistance(currentLat, currentLng, nextLat, nextLng);
                        }
                    }
                }
            }
        }
        return tramTrajectories;
    }

    public static TramTrajectory findClosestTrajectory(List<TramTrajectory> tramTrajectories, double currentLat, double currentLng) {
        TramTrajectory closestTrajectory = null;
        double minDistance = 10; // metres

        for (TramTrajectory trajectory : tramTrajectories) {
            double distance = calculateDistance(currentLat, currentLng, trajectory.getLatitude(), trajectory.getLongitude());
            if (distance < minDistance) {
//                minDistance = distance;
                closestTrajectory = trajectory;
            }
        }

        return closestTrajectory;
    }
}
