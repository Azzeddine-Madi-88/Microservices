package com.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardCodedRestResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardCodedRestResponse")
    //@RateLimiter(name = "default") //10s => allows 10 calls to the sample-api
    @Bulkhead(name = "sample-api") //How many concurrent calls are allowed
    public String sampleApi() {
//        logger.info("Sample API call received");
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
//        return forEntity.getBody();
        return "Sample Api";
    }

    public String hardCodedRestResponse(Exception throwable) {
        return "fallback-response" + throwable.getMessage();
    }
}
