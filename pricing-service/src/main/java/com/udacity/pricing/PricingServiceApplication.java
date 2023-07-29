package com.udacity.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.udacity.pricing.enity.price.Price;
import com.udacity.pricing.repository.PriceRepository;

/**
 * Creates a Spring Boot Application to run the Pricing Service. TODO: Convert
 * the application from a REST API to a microservice.
 */
@SpringBootApplication
@EnableEurekaClient
public class PricingServiceApplication {

    @Autowired
    private PriceRepository priceRepository;

    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

    @PostConstruct
    private void postConstruct() {
        for (long i = 1; i <= 10; i++) {
            priceRepository.save(new Price("USD", randomPrice(), i));
        }
    }

    private BigDecimal randomPrice() {
        return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5)).multiply(new BigDecimal(5000d)).setScale(2,
                RoundingMode.HALF_UP);
    }

}
