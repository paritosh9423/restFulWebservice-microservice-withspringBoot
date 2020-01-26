package com.paritosh.rest.webservices.restfulwebservices.filteringControllers;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	@GetMapping("/Nofiltering]")
	public MappingJacksonValue getSomeBean(){
		
		SomeBean somebean = new SomeBean("val1", "val2", "val3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(somebean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("val1","val2","val3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter );
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
		
		
	}
	
	@GetMapping("/filteringDynamic")
	public MappingJacksonValue getSomeBeanDynamic(){
		SomeBean somebean = new SomeBean("val1", "val2", "val3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(somebean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("val1","val2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter );
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
		
	}
}
