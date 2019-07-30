package ro.esolutions.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.esolutions.coffee.repositories.CoffeeRepository;

@SpringBootApplication
public class CoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeApplication.class, args);
	}

	@Autowired
	private CoffeeRepository coffeeRepository;


}
