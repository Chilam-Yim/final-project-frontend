package FinalProject_frontend.service;

import FinalProject_frontend.model.DistributionCenter;
import FinalProject_frontend.model.Item;
import FinalProject_frontend.model.WareHouse;
import FinalProject_frontend.model.dto.DistributionCenterDto;
import FinalProject_frontend.repository.DistributionCenterRepository;
import FinalProject_frontend.repository.WareHouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:8081")
public class ItemRequestService {

    private RestTemplate restTemplate;
    private WareHouseRepository wareHouseRepository;
    private DistributionCenterRepository distributionCenterRepository;


//    public ItemRequestService(WareHouseRepository wareHouseRepository, DistributionCenterRepository distributionCenterRepository) {
//        this.wareHouseRepository = wareHouseRepository;
//        this.distributionCenterRepository = distributionCenterRepository;
//    }
//
//    public DistributionCenterDto requestItem(@PathVariable long id, String brand, String name, int quantity) {
//        var centers = Arrays.asList(restTemplate.getForObject("http://localhost:8081/api/center", DistributionCenterDto[].class));
//        WareHouse wareHouse = wareHouseRepository.findById(id).get();
//        DistributionCenterDto closestCenter = findClosestDistributionCenter(centers, wareHouse.getLatitude(), wareHouse.getLongitude());
//        return closestCenter;
//    }
//

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
