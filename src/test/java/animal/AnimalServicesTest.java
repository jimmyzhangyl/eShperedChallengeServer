package animal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Assume the data given is already validated, only test for the functionality
 * 
 * @author Yuanlong Zhang
 * @date 12 Aug 2022
 */
class AnimalServicesTest {
	private AnimalServices animalServices = new AnimalServices();

	@Test
	void testUpdate() {
		Animal animal = new Animal("test", 10, new Location(5.00001, 5.00001));
		String response = animalServices.update(0, animal);
		// return value same as input value
		assertEquals(response, animal.toString());
	}
	
	/**
	 * expecting the result of {"name":"20charName"}
	 */
	@Test
	void testGetNewName() {
		String response = animalServices.setNewName();
		assertNotNull(response);
		assertEquals(response.length(), 31);
	}
	
	@Test
	void testGetAnimals() {
		String response = animalServices.getAnimals();
		assertEquals(response.getClass(), String.class);
	}

}
