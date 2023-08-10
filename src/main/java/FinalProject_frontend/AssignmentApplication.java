package FinalProject_frontend;

import FinalProject_frontend.model.Item;
import FinalProject_frontend.model.WareHouse;
import FinalProject_frontend.repository.ItemRepository;
import FinalProject_frontend.repository.WareHouseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}


	@Bean
	public CommandLineRunner wareHouseDataLoader(WareHouseRepository repository){
		return args -> {
			repository.save(WareHouse.builder()
					.name("Abc WareHouse")
					.latitude(43.651070)
					.longitude(-79.347015).build());
		};
	}


	@Bean
	public CommandLineRunner itemDataLoader(ItemRepository repository) {
		return args -> {
			repository.save(Item.builder()
					.name("Small Nomad Bag")
					.createYear(2024)
					.brandFrom(Item.Brand.DIOR)
					.price(new BigDecimal(4700))
					.quantity(1).build());

			repository.save(Item.builder()
					.name("B30 Sneaker")
					.createYear(2023)
					.brandFrom(Item.Brand.DIOR)
					.price(new BigDecimal(1350)).quantity(1).build());

			repository.save(Item.builder()
					.name("Sweater")
					.createYear(2022)
					.brandFrom(Item.Brand.DIOR)
					.price(new BigDecimal(4000)).quantity(1).build());

			repository.save(Item.builder()
					.name("Box Logo Hoodie")
					.createYear(2022)
					.brandFrom(Item.Brand.SUPREME)
					.price(new BigDecimal(1100)).quantity(1).build());

			repository.save(Item.builder()
					.name("Box Logo Backpack")
					.createYear(2022)
					.brandFrom(Item.Brand.SUPREME)
					.price(new BigDecimal(2100)).quantity(1).build());

			repository.save(Item.builder()
					.name("Tourist varsity \"Black\" jacket")
					.createYear(2022)
					.brandFrom(Item.Brand.SUPREME)
					.price(new BigDecimal(1350)).quantity(1).build());

			repository.save(Item.builder()
					.name("Air Jordan 1 Retro High Off-White Chicago")
					.createYear(2022)
					.brandFrom(Item.Brand.OFFWHITE)
					.price(new BigDecimal(7000)).quantity(1).build());

			repository.save(Item.builder()
					.name("Script Logo Opp Over Skate Hoodie")
					.createYear(2022)
					.brandFrom(Item.Brand.OFFWHITE)
					.price(new BigDecimal(1065)).quantity(1).build());

			repository.save(Item.builder()
					.name("Nike Air Force 1 Low Off-White")
					.createYear(2023)
					.brandFrom(Item.Brand.OFFWHITE)
					.price(new BigDecimal(2500)).quantity(1).build());

			repository.save(Item.builder()
					.name("Nike Air Force 1 Low Off-White Volt")
					.createYear(2024)
					.brandFrom(Item.Brand.OFFWHITE)
					.price(new BigDecimal(2100)).quantity(1).build());
		};
	}
}
