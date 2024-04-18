package az.code.trammanagementsystem.services.helpers;

import az.code.trammanagementsystem.entity.Stop;

import java.util.List;

public class DistanceHelper {
    public static boolean stopTooCloseToOther(Stop newStop, List<Stop> existingStops) {
        // Define the minimum distance threshold (50 meters)
        final double MIN_DISTANCE_METERS = 50.0;

        // Iterate through each existing stop
        for (Stop existingStop : existingStops) {
            // Calculate the distance between the new stop and the existing stop
            double distance = calculateDistance(
                    newStop.getLatitude(), newStop.getLongitude(),
                    existingStop.getLatitude(), existingStop.getLongitude()
            );

            // Check if the distance is less than the minimum threshold
            if (distance < MIN_DISTANCE_METERS) {
                // If the distance is too close, return true
                return true;
            }
        }
        // If no existing stop is too close, return false
        return false;
    }

    // Method to calculate distance between two points (in meters)
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000;
    }
}
