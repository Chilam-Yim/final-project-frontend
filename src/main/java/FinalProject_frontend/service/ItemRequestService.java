package FinalProject_frontend.service;

import FinalProject_frontend.model.dto.DistributionCenterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemRequestService {


    public DistributionCenterDto findClosestDistributionCenter(List<DistributionCenterDto> centers, double targetLatitude, double targetLongitude) {
        DistributionCenterDto closestCenter = null;
        double closestDistance = Double.MAX_VALUE;

        for (DistributionCenterDto center : centers) {
            double distance = calculateDistance(center.getLatitude(), center.getLongitude(), targetLatitude, targetLongitude);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestCenter = center;
            }
        }
        return closestCenter;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
