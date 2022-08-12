package animal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * Model class for animal object
 * @author Yuanlong Zhang
 *
 */
public class Animal {

	@NotNull
	private String name;

	// constraint: animal age between 0-25 years
	@NotNull
	@Range(min = 0, max = 300)
	private int months;
	
	@NotNull
	@Valid
	private Location location;

	public Animal(String name, int months, Location location) {
		super();
		this.name = name;
		this.months = months;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("{\"name\":\"%s\",\"months\":%d,\"location\":%s}", this.getName(), this.getMonths(),
				this.getLocation());
	}

}
