package animal;


import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import utilities.DefaultAnimals;

public class AnimalServices {
	private String db, name;

	public AnimalServices() {
		this.db = "eshepherd-challenge-default-rtdb.asia-southeast1.firebasedatabase.app";
		this.setNewName();
	}

	/**
	 * patch to db with given object
	 * 
	 * @param index
	 * @param animal
	 * @return server response
	 */
	public String update(int index, Animal animal) {
		// update using RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(6000);
		requestFactory.setReadTimeout(6000);
		restTemplate.setRequestFactory(requestFactory);

		String url = String.format("https://%s/%s/animals/%d/.json", this.db, this.name, index);
		String response = restTemplate.patchForObject(url, animal, String.class);
		
		return (response);
	}
	
	/**
	 * Using default dataSet to generate and use a new name
	 * @return
	 */
	public String setNewName() {
		DefaultAnimals defaultAnimals = new DefaultAnimals();
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("https://%s/.json", this.db);
		
		String response = restTemplate.postForObject(url, defaultAnimals.getDataSet(), String.class);
		JSONObject json = new JSONObject(response);
		this.name = json.getString("name");
		return (response);
	}
	
	/**
	 * return a array of animals (Animal[]) in string
	 * @return
	 */
	public String getAnimals() {
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("https://%s/%s.json", this.db, this.name);
		
		String response = restTemplate.getForObject(url, String.class);
		JSONObject json = new JSONObject(response);
		
		return (json.getJSONArray("animals").toString());
	}
	
	/**
	 * reset db with default data set
	 * @return
	 */
	public void resetDB() {
		DefaultAnimals defaultAnimals = new DefaultAnimals();
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("https://%s/%s.json", this.db, this.name);
		
		restTemplate.put(url, defaultAnimals.getDataSet());
	}
	

	public String getName() {
		return name;
	}

}
