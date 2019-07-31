package ro.esolutions.coffee;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ro.esolutions.coffee.entities.Coffee;

import static org.junit.Assert.assertEquals;

public class CoffeeApplicationIT {

	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void givenId_whenGetCoffeeEndpointIsCalled_ThenReturnHttpStatusNotFound() {
		//given
		String id = "badId";

		try {
			//when
			restTemplate.getForEntity("http://localhost:8080/coffee/{id}", Coffee.class, id);
		} catch (HttpClientErrorException.NotFound ex) {
			//then
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}

	}

	@Test(expected = HttpClientErrorException.NotFound.class)
	public void givenId_whenGetCoffeeEndpointIsCalled_ThenReturnHttpStatus404() {
		//given
		String id = "badId";

		//when
		ResponseEntity<Coffee> actualResult = restTemplate.getForEntity("http://localhost:8080/coffee/{id}", Coffee.class, id);

		//then
		assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
	}

	@Test
	public void givenId_whenGetCoffeeEndpointIsCalled_ThenReturnHttpStatus200() {
		//given
		String id = "1";

		//when
		ResponseEntity<Coffee> actualResult = restTemplate.getForEntity("http://localhost:8080/coffee/{id}", Coffee.class, id);

		//then
		assertEquals(HttpStatus.OK, actualResult.getStatusCode());
	}

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
