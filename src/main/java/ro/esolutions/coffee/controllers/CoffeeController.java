package ro.esolutions.coffee.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.coffee.entities.Coffee;
import ro.esolutions.coffee.repositories.CoffeeRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class CoffeeController {

	private final CoffeeRepository coffeeRepository;

	@Value("${coffee.docs.directory}")
	private String coffeeDocsDirectory;

	@Autowired
	public CoffeeController(CoffeeRepository coffeeRepository) {
		this.coffeeRepository = coffeeRepository;
	}

	@GetMapping("/coffee/{id}")
	public ResponseEntity<Coffee> getById(final @PathVariable String id) {
		return coffeeRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/coffee")
	public void writeCoffeeOnDisk(final @RequestBody Coffee coffee) {
		File directory = new File(coffeeDocsDirectory);
		if (!directory.exists()) {
			directory.mkdir();
		}


		ObjectMapper mapper = new ObjectMapper();
		File file = new File(coffeeDocsDirectory + "/" + UUID.randomUUID().toString() + ".json");

		try {
			mapper.writeValue(file, coffee);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

