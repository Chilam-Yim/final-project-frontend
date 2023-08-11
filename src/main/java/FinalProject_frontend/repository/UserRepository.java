package FinalProject_frontend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import FinalProject_frontend.model.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long>{
    AppUser findByUsername(String username);
}
