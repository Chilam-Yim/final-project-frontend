package FinalProject_frontend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import FinalProject_frontend.model.Item;

public interface ItemRepositoryPaginated extends PagingAndSortingRepository<Item, Long>{
}
