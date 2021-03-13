package com.islateste.demo.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoAllConstrictor 
@AllArgsConstructor
public class WeatherOfCity {

	private Long city; 
    private Data data;
    private Time time;
    private BigDecimal temp;
	private String weather;  
    private List <Link> links;

public WeatherOfCity(WeatherOfCityTypeEnum type){
		this.type = type;
	}
	
public void addLink(Link link) {
		if(links == null) links = new ArrayList<>();
		links.add(link);
	}
    	
}