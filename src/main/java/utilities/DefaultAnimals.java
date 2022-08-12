package utilities;

import java.util.HashMap;
import java.util.Random;

import animal.Animal;
import animal.Location;

public class DefaultAnimals {
	private HashMap<String, Animal[]> dataSet;
	Animal[] animalList = new Animal[] {
			new Animal("Max", 14, new Location(randomLastThreeDecimal("-37.84"), randomLastThreeDecimal("145.07"))),
			new Animal("Muriel", 32, new Location(randomLastThreeDecimal("-37.81"), randomLastThreeDecimal("145.04"))),
			new Animal("Mildred", 32, new Location(randomLastThreeDecimal("-37.82"), randomLastThreeDecimal("145.05"))),
			new Animal("Mortimer", 32,
					new Location(randomLastThreeDecimal("-37.82"), randomLastThreeDecimal("145.06"))),
			new Animal("Melville", 32,
					new Location(randomLastThreeDecimal("-37.43"), randomLastThreeDecimal("145.45"))),

	};

	public DefaultAnimals() {
		dataSet = new HashMap<>();
		dataSet.put("animals", animalList);
	}

	private double randomLastThreeDecimal(String num) {
		Random random = new Random();
		String newNum = num + random.nextInt(900) + 100;
		return Double.parseDouble(newNum);
	}

	public HashMap<String, Animal[]> getDataSet() {
		return dataSet;
	}
	
	
}
