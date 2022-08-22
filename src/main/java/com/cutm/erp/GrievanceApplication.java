package com.cutm.erp;

import com.cutm.erp.grievance.controller.HibernateAwareObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GrievanceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GrievanceApplication.class, args);
        System.out.println("Welcome to Centurion University");
    }

    @Bean
    public ObjectMapper objectMapper() {
        return (new HibernateAwareObjectMapper());
    }

}
