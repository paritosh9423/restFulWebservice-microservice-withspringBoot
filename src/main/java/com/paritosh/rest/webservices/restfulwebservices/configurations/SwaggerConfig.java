package com.paritosh.rest.webservices.restfulwebservices.configurations;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Configuration file
//Enable Swagger
@Configuration
@EnableSwagger2 
public class SwaggerConfig {
//bean docket
	//swagger2
	//paths
	//APIs
	public static final springfox.documentation.service.Contact DEFAULT_CONTACT = new springfox.documentation.service.Contact("Paritosh Pradeep", "Not vailable yet", "myEmail@gmail.com");
	//public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation", "RestFul WebService Project by Paritosh",
		//											"1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation", "RestFul WebService Project by Paritosh", 
			"1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	public static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>() ;
	static{
		DEFAULT_PRODUCES_AND_CONSUMES.add("application/json");
		DEFAULT_PRODUCES_AND_CONSUMES.add("application/xml");
	}
/*	Arrays.
	Arrays.asList("application/json","application/xml");*/ 
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
	
}
