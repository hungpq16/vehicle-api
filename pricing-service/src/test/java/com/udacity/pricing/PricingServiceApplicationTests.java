package com.udacity.pricing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.udacity.pricing.domain.price.Price;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

    private TestRestTemplate restTemplate;

    public PricingServiceApplicationTests(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getPrice() {
        long vehicleID = 1L;
        ResponseEntity<Price> response = this.restTemplate
                .getForEntity("http://localhost:" + this.port + "/services/price?vehicleId=" + vehicleID, Price.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
