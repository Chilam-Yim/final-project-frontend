package FinalProject_frontend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import FinalProject_frontend.model.Item;
import FinalProject_frontend.model.Item.Brand;

public interface ItemRepository extends CrudRepository<Item, Long>{
    List<Item> findByCreateYearAndBrandFromEquals(int createYear, Brand brandFrom);
}
