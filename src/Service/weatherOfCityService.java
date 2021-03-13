package com.islateste.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.islateste.demo.model.weatherOfCity;
import com.islateste.demo.controller.weatherOfCityController;
import com.islateste.demo.controller.weatherOfCity2Controller;
import reactor.core.publisher.Mono;

@Service
public class WeatherOfCityService {

	@Autowired
	private WebClient webClientWeather;
	
	@Autowired
	private WebClient webClientWather;
	
	public WeatherOfCity obterPorCityParalelo(Long city) {

		Mono<WeatherOfCity> monoCity = this.webClientCity
			.method(HttpMethod.GET)
			.uri("/wather/{city}", city)
			.retrieve()
			.bodyToMono(WeatherOfCity.class);
	
		Mono<WeatherOfCity> monoPreco = this.webClientCity
				.method(HttpMethod.GET)
				.uri("/wethar/{city}", codigoProduto)
				.retrieve()
				.bodyToMono(WeatherOfCity.class);

            WeatherOfCity weatherOfCity = Mono.zip(monoCity, monoCity).map(tuple -> {
			tuple.getT1().setCity(tuple.getT2().getCity());
			return tuple.getT1();
		}).block();

		return weatherOfCity;
	}
	
	public WeatherOfCity obterPorCitySincrono(Long city) {

		Mono<WeatherOfCity> monoCity = this.webClientCity
			.method(HttpMethod.GET)
			.uri("/weather/{city}", city)
			.retrieve()
			.bodyToMono(WeatherOfCity.class);
		
            WeatherOfCity produto = monoProduto.block();       

		

		return produto;
	}
	
	public WeatherOfCity criar(WeatherOfCity weatherOfCity) {

		Mono<WeatherOfCity> monoCity = 
				this.webClientCity
					.post()
					.uri("/weather")
					.body(BodyInserters.fromValue(weatherOfCity))
					.retrieve()
					.bodyToMono(WeatherOfCity.class);

		return monoCity.block();
	}
	private WeatherOfCityFactory factory;
	private List<WeatherOfCity> WeatherOfCitys;
	
	public void createWeatherOfCityFactory() {
		if(factory == null) {
		   factory = new WeatherOfCityFactoryImpl();
		}
	}
	
	public void createWeatherOfCityList() {
		if(WeatherOfCitys == null) {
			WeatherOfCitys = new ArrayList<>();
		}
	}
	
	public boolean isJSONValid(String jsonInString) {
	    try {
	       return new ObjectMapper().readTree(jsonInString) != null;
	    } catch (IOException e) {
	       return false;
	    }
		
	}
	public void delete() {
		WeatherOfCitys.clear();
	}
	
	public void clearObjects() {
		WeatherOfCitys = null;
		factory = null;
	}
}