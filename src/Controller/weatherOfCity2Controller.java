package com.islateste.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.islateste.demo.model.weatherOfCity;
import com.islateste.demo.controller.weatherOfCityController;
import com.islateste.service.weatherOfCityService;

@RestController
public class weatherOfCity2Controller {

	@Autowired
	private WeatherOfCityService weatherOfCityService;
	
	@GetMapping("/weather/{city}")
	public ResponseEntity<WeatherOfCity> obterWeatherOfCity(@PathVariable Long city) {
		
		WeatherOfCity weatherOfCity = this.weatherOfCityService.obterPorCitySincrono(city);

		return ResponseEntity.ok(WeatherOfCity);
	}

	@GetMapping("/weather/{city}async")
	public ResponseEntity<WeatherOfCity> obterWeatherOfCityParalelo(@PathVariable Long city) {
		
		WeatherOfCity weatherOfCity = this.WeatherOfCityService.obterPorCityParalelo(city);

		return ResponseEntity.ok(WeatherOfCity);
	}

	@PostMapping("/weather")
	public ResponseEntity<WeatherOfCity> criarWeather(@RequestBody WeatherOfCity weather) {

		WeatherOfCity WeatherOfCity = this.WeatherOfCityService.criar(weather);

		return ResponseEntity.ok(WeatherOfCity);
	}
}