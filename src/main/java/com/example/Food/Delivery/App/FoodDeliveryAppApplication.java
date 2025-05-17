package com.example.Food.Delivery.App;

import com.example.Food.Delivery.App.entities.OrderStatus;
import com.example.Food.Delivery.App.repositories.OrderStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FoodDeliveryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryAppApplication.class, args);
	}


	@Bean
	public CommandLineRunner seedOrderStatuses(OrderStatusRepository orderStatusRepository) {
		return args -> {
			List<String> defaultStatuses = List.of("Pending", "Confirmed", "Preparing", "On the way", "Delivered", "Cancelled");

			for (String status : defaultStatuses) {
				orderStatusRepository.findByName(status)
						.orElseGet(() -> orderStatusRepository.save(new OrderStatus(status)));
			}
		};
	}

}
