package com.anthonyra95.covid;

import com.anthonyra95.covid.contollers.StateController;
import com.anthonyra95.covid.domain.Data;
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
	@Bean
	public CommandLineRunner initData(StateRepository repository) throws Exception {
		return args -> {
//			//load Covid data for each of the states
//			Data data = new Data();
//			data.populateData();

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CovidApplication.class, args);
	}
}
