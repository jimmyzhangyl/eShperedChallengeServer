package animal;

import java.util.LinkedHashMap;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import utilities.Logger;

/**
 * Controller for all API end points
 * 
 * @author Yuanlong Zhang
 * @date 11 Aug 2022
 */
@CrossOrigin(maxAge = 3600)
@RestController
@Validated
public class AnimalController {
	private AnimalServices animalServices = new AnimalServices();

	/**
	 * return currently used name value
	 * 
	 * @return
	 */
	@GetMapping("/name")
	public ResponseEntity<String> getName() {
		Logger logeer = new Logger();
		logeer.addHeader("Calling API [get /name]");
		String response = animalServices.getName();
		logeer.addActivity("Get name-" + response);
		logeer.close();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/newName")
	public ResponseEntity<String> setNewName() {
		Logger logeer = new Logger();
		logeer.addHeader("Calling API [get /newName]");
		String response = animalServices.setNewName();
		logeer.addActivity("Create name-" + response);
		logeer.close();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/animals")
	public ResponseEntity<String> getAnimals() {
		Logger logeer = new Logger();
		logeer.addHeader("Calling API [get /animals]");
		String response = animalServices.getAnimals();
		logeer.addActivity("Get name-" + response);
		logeer.close();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/resetAnimals")
	public ResponseEntity<String> resetAnimal() {
		Logger logeer = new Logger();
		logeer.addHeader("Calling API [get /resetAnimals]");
		animalServices.resetDB();
		logeer.close();
		return new ResponseEntity<>("db restted", HttpStatus.OK);
	}
	
	/**
	 * update the whole given animal objects into db
	 * 
	 * @param animals
	 * @return
	 */
	@PostMapping("/animals")
	public ResponseEntity<Object> updateAnimals(@RequestBody LinkedHashMap<String, @Valid Animal> animals) {
		Logger logeer = new Logger();
		logeer.addHeader("Calling API [post /animals]");

		Set<String> keys = animals.keySet();
		try {
			for (String key : keys) {
				String response = animalServices.update(Integer.parseInt(key), animals.get(key));
				// log activity
				logeer.addActivity("Update-" + response);
			}
		} catch (Exception e) {
			logeer.close();
			return new ResponseEntity<>("updation failed|" + e, HttpStatus.BAD_REQUEST);
		}
		logeer.close();
		return new ResponseEntity<>(animals, HttpStatus.OK);
	}

}
