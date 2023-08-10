package FinalProject_frontend.repository;



import FinalProject_frontend.model.DistributionCenter;
import FinalProject_frontend.model.Item;
import FinalProject_frontend.model.dto.DistributionCenterDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistributionCenterRepository extends CrudRepository<DistributionCenter, Long> {


}
