package ro.esolutions.coffee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import ro.esolutions.coffee.entities.Coffee;

import static org.junit.Assert.assertEquals;

public class CoffeeApplicationTests {

	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void givenId_whenGetCoffeeEndpointIsCalled_ThenReturnCoffee() {
		//given
		String id = "1";
		Coffee expectedCoffee = new Coffee("1", "Espresso");

		//when
		Coffee actualResult = restTemplate.getForObject("http://localhost:8080/coffee/{id}", Coffee.class, id);

		//then
		assertEquals(expectedCoffee, actualResult);

	}

}
