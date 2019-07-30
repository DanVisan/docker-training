package ro.esolutions.coffee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.esolutions.coffee.entities.Coffee;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, String> {
}
