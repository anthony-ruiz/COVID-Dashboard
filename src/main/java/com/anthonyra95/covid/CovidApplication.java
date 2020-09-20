package com.anthonyra95.covid;

import com.anthonyra95.covid.domain.State;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.List;

@SpringBootApplication
public class CovidApplication {

	private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CovidApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {

			ObjectMapper mapper = new ObjectMapper();
			List<State> stateList = mapper.readValue(new URL("https://api.covidtracking.com/v1/states/current.json"), new TypeReference<List<State>>() {});

			for(State state: stateList){
				log.info(state.toString());
			}
			//mapper.
			//List<State> myStates = mapper.readValue("https://api.covidtracking.com/v1/states/current.json", new TypeReference<List<State>>(){});

//			State state = restTemplate.getForObject(
//					"https://api.covidtracking.com/v1/states/current.json", State.class, mapper);
//			log.info(state.toString());
		};
	}
}
