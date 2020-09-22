package com.anthonyra95.covid;

import com.anthonyra95.covid.contollers.MapServlet;
import com.anthonyra95.covid.domain.State;

import com.anthonyra95.covid.domain.StateRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class CovidApplication {


	//creates a logger
	private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);
	//-----LOAD DATABASE-----
	@Bean
	public CommandLineRunner initData(StateRepository repository) throws Exception {
		return args -> {
			log.info("loading Database");
			ObjectMapper mapper = new ObjectMapper();
			List<State> stateList = mapper.readValue(new URL("https://api.covidtracking.com/v1/states/current.json"), new TypeReference<List<State>>() {});
			//print the states  and number of cases
			for(State state: stateList){
				repository.save(state);
				log.info("saving state: " +  state.toString());
			}
			log.info("Database loaded successfully");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CovidApplication.class, args);
	}
}
