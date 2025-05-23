package com.example.Food.Delivery.App;

import com.example.Food.Delivery.App.entities.OrderStatus;
import com.example.Food.Delivery.App.enums.OrderStatusType;
import com.example.Food.Delivery.App.repositories.OrderStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class FoodDeliveryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryAppApplication.class, args);
	}


	@Component
	public class OrderStatusSeeder implements CommandLineRunner {

		private final OrderStatusRepository orderStatusRepository;

		public OrderStatusSeeder(OrderStatusRepository orderStatusRepository) {
			this.orderStatusRepository = orderStatusRepository;
		}

		@Override
		public void run(String... args) {
			for (OrderStatusType type : OrderStatusType.values()) {
				orderStatusRepository.findByStatusType(type).orElseGet(() ->
						orderStatusRepository.save(new OrderStatus(type))
				);
			}
		}
	}

}
