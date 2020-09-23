package com.anthonyra95.covid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CovidApplication {


	//creates a logger
	private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);
	@Bean
	public CommandLineRunner initData() throws Exception {
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
