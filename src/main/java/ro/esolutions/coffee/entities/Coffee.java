package ro.esolutions.coffee.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Coffee {
	@Id
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coffee() {
	}

	public Coffee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coffee coffee = (Coffee) o;
		return Objects.equals(id, coffee.id) &&
				Objects.equals(name, coffee.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
