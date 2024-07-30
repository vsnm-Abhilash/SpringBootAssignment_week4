package com.abhilash.sb.assignment_w4.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {
    @Value("${employeeService.base.url}")
    private String BASE_URL;
    @Value("${exchangeRatesService.base.url}")
    private String CURRENCY_EXCHANGE_URL;
    @Value("${exchangeRatesService.API_KEY}")
    private String API_KEY;

    @Bean
    @Qualifier("employeeRestClient")
    RestClient getEmployeeServiceRestClient(){
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(request, response) -> {
                    throw new RuntimeException("Server error occurred ");
                })
                .build();
    }

    @Bean
    @Qualifier("currencyRestClient")
    RestClient getCurrencyServiceRestClient(){
        return RestClient.builder()
                .baseUrl(CURRENCY_EXCHANGE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(request, response) -> {
                    throw new RuntimeException("Server error occurred ");
                })
                .build();
    }
}
