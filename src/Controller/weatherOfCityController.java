package com.islateste.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import com.islateste.demo.weather.model.Weather;

@RestController
@RequestMapping("/weather")
public class weatherOfCityController {

	@GetMapping("/{city}")
	public ResponseEntity<Weather> obterWeather(@PathVariable Long city) throws Exception {

		Weather weather = new Weather(city);
		
		return ResponseEntity.ok(weather);
	}
	
	@PostMapping
	public ResponseEntity<Weather> criar(@RequestBody Weather weather) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(eather);
	}

	private static final Logger logger = Logger.getLogger(weatherOfCityController.class);
	
	@Autowired
	private WeatherOfCityService weatherOfCityService;
	
	@GetMapping
	public ResponseEntity<List<WeatherOfCity>> find() {
		if(weatherOfCityService.find().isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		logger.info(weatherOfCityService.find());
		return ResponseEntity.ok(weatherOfCityService.find());
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> delete() {
		try {
			WeatherOfCityService.delete();
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
		
	@ResponseBody
	public ResponseEntity<WeatherOfCity> create(@RequestBody JSONObject weatherOfCity) {
		try {
			if(WeatherOfCityService.isJSONValid(WeatherOfCity.toString())) {
				WeatherOfCity WeatherOfCityCreated = WeatherOfCityService.create(WeatherOfCity);
				var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(WeatherOfCitylCreated.getOrderNumber()).build().toUri();			
				
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			logger.error("JSON fields are not parsable. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PutMapping(path = "/{city}", produces = { "application/json" })
	public ResponseEntity<WeatherOfCity> update(@PathVariable("city") long city, @RequestBody JSONObject WeatherOfCity) {
		try {
			if(WeatherOfCityService.isJSONValcity(WeatherOfCity.toString())) {
				WeatherOfCity WeatherOfCityToUpdate = WeatherOfCityService.findByCity(city);
				if(WeatherOfCityToUpdate == null){
					logger.error("Weather not found.");
					return ResponseEntity.notFound().build(); 
				}else {
					WeatherOfCity WeatherCityToUpdate = WeatherOfCityService.update(WeatherOfCityToUpdate, WeatherOfCity);
					return ResponseEntity.ok(WeatherOfCityToUpdate);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			logger.error("JSON fields are not parsable." + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
}