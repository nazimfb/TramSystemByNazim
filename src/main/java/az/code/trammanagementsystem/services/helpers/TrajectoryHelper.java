package az.code.trammanagementsystem.services.helpers;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.TramTrajectory;
import az.code.trammanagementsystem.entity.Waypoint;

import java.util.ArrayList;
import java.util.List;

import static az.code.trammanagementsystem.services.helpers.DistanceHelper.calculateDistance;


public class TrajectoryHelper {
    public static List<TramTrajectory> getTramTrajectories(Route route) {
        List<Waypoint> waypoints = route.getWaypoints();
        List<TramTrajectory> tramTrajectories = new ArrayList<>();

        // Iterate through waypoints to create tram trajectories
        for (int i = 0; i < waypoints.size(); i++) {
            Waypoint currentWaypoint = waypoints.get(i);
            double currentLat = currentWaypoint.getLat();
            double currentLng = currentWaypoint.getLng();

            // Add the first tram trajectory point
            TramTrajectory tramTrajectory = new TramTrajectory();
            tramTrajectory.setLatitude(currentLat);
            tramTrajectory.setLongitude(currentLng);
            tramTrajectory.setRoute(route);
            tramTrajectories.add(tramTrajectory);

            // Calculate the next tram trajectory points
            for (int j = i + 1; j < waypoints.size(); j++) {
                Waypoint nextWaypoint = waypoints.get(j);
                double nextLat = nextWaypoint.getLat();
                double nextLng = nextWaypoint.getLng();

                // Calculate distance between current and next waypoints
                double distance = calculateDistance(currentLat, currentLng, nextLat, nextLng);

                // If distance is greater than 10m, add tram trajectory points
                while (distance > 40) {
                    double fraction = 10 / distance;
                    double newLat = currentLat + (nextLat - currentLat) * fraction;
                    double newLng = currentLng + (nextLng - currentLng) * fraction;

                    // Create and add tram trajectory point
                    TramTrajectory newTrajectory = new TramTrajectory();
                    newTrajectory.setLatitude(newLat);
                    newTrajectory.setLongitude(newLng);
                    newTrajectory.setRoute(route);
                    tramTrajectories.add(newTrajectory);

                    // Update current coordinates
                    currentLat = newLat;
                    currentLng = newLng;

                    // Update distance
                    distance = calculateDistance(currentLat, currentLng, nextLat, nextLng);
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
