package FinalProject_frontend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import FinalProject_frontend.model.UserDetail;

@Repository
public interface UserRepository extends CrudRepository<UserDetail, Long>{
    UserDetail findByUsername(String username);
}
