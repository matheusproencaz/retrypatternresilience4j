package io.github.mpz.retrypattern.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/v1/teste")
@RequiredArgsConstructor
public class ControllerTest {

    private static final String SERVICE_NAME = "serviceName";
    private static final String BASE_URL = "http://localhost:1111/teste-retry";

    private int attempt=1;
    private final RestTemplate restTemplate;

    @GetMapping
    @Retry(name = SERVICE_NAME, fallbackMethod = "fallbackMethod")
    public String getTeste() throws IOException {
        System.out.printf("Number of attemps %d, time: %s\n",attempt++, System.currentTimeMillis());
         var response = restTemplate.exchange(BASE_URL,
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                String.class
        );
        System.out.println(response.getBody());
        return response.getBody();
    }

    private String fallbackMethod(Exception e) {
        // TODO: Imagine alguma lógica aqui
        System.out.println(e.getMessage());
        return "O pedido está sendo processado quando terminar você receberá no seu e-mail.";
    }
}
