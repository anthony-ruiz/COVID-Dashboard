package com.anthonyra95.covid;

import com.anthonyra95.covid.domain.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CovidApplication {

    //Contains all state COVID data for the USA
    public static Data data = new Data();

    //initializes data at launch
    @Bean
    public CommandLineRunner initData() throws Exception {
        return args -> {
            data.populateData();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CovidApplication.class, args);
    }

    public static Data getData() {
        return data;
    }
}
