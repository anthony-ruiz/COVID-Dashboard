package com.anthonyra95.covid;

import com.anthonyra95.covid.domain.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CovidApplication {

	//initialized data at launch
	public static Data data = new Data();

	//creates a logger
	private static final Logger log = LoggerFactory.getLogger(CovidApplication.class);
	@Bean
	public CommandLineRunner initData() throws Exception {
		return args -> {
//			//load Covid data for each of the states

			data.populateData();

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CovidApplication.class, args);
	}

	public static Data getData(){
		return data;
	}
}
