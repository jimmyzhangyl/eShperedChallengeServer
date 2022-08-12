package animal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPostAnimalValidInput() throws Exception {
		Animal validInput = new Animal("Test", 10, new Location(5, 5));
		HashMap<String, Animal> input = new HashMap<>();
		input.put("0", validInput);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/animals").content(asJsonString(input))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpectAll(MockMvcResultMatchers.jsonPath("$.0.name").value("Test"),
						MockMvcResultMatchers.jsonPath("$.0.months").value(10),
						MockMvcResultMatchers.jsonPath("$.0.location.lat").value(5),
						MockMvcResultMatchers.jsonPath("$.0.location.lon").value(5));
	}

	/**
	 * Constraint month Range(0-300) NotNUll
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPostAnimalInvalidMonth() throws Exception {
		Animal tooBigMonth = new Animal("Test", 1000, new Location(5, 5));
		Animal tooSmallMonth = new Animal("Test", -1000, new Location(5, 5));

		testPostWithInvalidParameters(tooBigMonth, "must be between 0 and 300");
		testPostWithInvalidParameters(tooSmallMonth, "must be between 0 and 300");
	}

	@Test
	public void testPostAnimalInvalidName() throws Exception {
		Animal nullName = new Animal(null, 10, new Location(5, 5));

		testPostWithInvalidParameters(nullName, "must not be null");
	}

	/**
	 * constraints lat range(-90,90) lon range(-180,180) location/lat/lon NotNull
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPostAnimalInvalidLocation() throws Exception {
		Animal smallLat = new Animal("Test", 10, new Location(-100, 5));
		Animal largeLat = new Animal("Test", 10, new Location(100, 5));
		Animal smallLon = new Animal("Test", 10, new Location(5, 205));
		Animal largeLon = new Animal("Test", 10, new Location(5, -205));
		Animal nullLocation = new Animal("Test", 10, null);

		testPostWithInvalidParameters(smallLat, "must be between -90 and 90");
		testPostWithInvalidParameters(largeLat, "must be between -90 and 90");
		testPostWithInvalidParameters(smallLon, "must be between -180 and 180");
		testPostWithInvalidParameters(largeLon, "must be between -180 and 180");
		testPostWithInvalidParameters(nullLocation, "must not be null");
	}

	/**
	 * constraint response size = 20
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetName() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/name")).andExpect(status().isOk())
				.andReturn();

		assertEquals(result.getResponse().getContentLength(), 20);
	}
	

	/**
	 * constraint response size = 31
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetNewName() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/newName")).andExpect(status().isOk())
				.andReturn();

		assertEquals(result.getResponse().getContentLength(), 31);
	}
	
	

	@Test
	public void testGetAnimals() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/animals")).andExpect(status().isOk());
	}
	
	@Test
	public void testResetAnimals() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/resetAnimals")).andExpect(status().isOk());
	}

	/**
	 * Helper method to simplify mock Post Action with invalid parameters
	 * 
	 * @param testCase
	 * @param value
	 * @throws Exception
	 */
	private void testPostWithInvalidParameters(Animal testCase, String value) throws Exception {
		LinkedHashMap<String, Animal> input = new LinkedHashMap<>();
		input.put("0", testCase);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/animals").content(asJsonString(input))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]").value(value));
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
