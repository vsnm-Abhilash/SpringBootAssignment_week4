package com.abhilash.sb.assignment_w4.services;

import com.abhilash.sb.assignment_w4.advices.ApiResponse;
import com.abhilash.sb.assignment_w4.advices.CurrencyResponse;
import com.abhilash.sb.assignment_w4.configs.RestClientConfig;
import com.abhilash.sb.assignment_w4.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    @Value("${exchangeRatesService.API_KEY}")
    private String API_KEY;

    Logger log= LoggerFactory.getLogger(CurrencyService.class);

    private final RestClient getCurrencyServiceRestClient;
    private final ModelMapper mapper;
    public Double convertCurrency(String fromCurrency,String toCurrency,Double units){
            try{
                ResponseEntity<String> response=getCurrencyServiceRestClient
                                        .get()
                                        .uri(uriBuilder -> uriBuilder.queryParam("apikey",API_KEY)
                                                                     .queryParam("base_currency",fromCurrency)
                                                                     .queryParam("currencies",toCurrency).build())
                                        .retrieve()
                        .toEntity(String.class);
                JSONObject jsonObject=new JSONObject(response.getBody());
                JSONObject data=jsonObject.getJSONObject("data");
                log.info("JSON returned from the server {}", String.valueOf(jsonObject));
                log.info("data in JSON {}",String.valueOf(data));
                Map<String,Object> rates=data.toMap();
                String[] currencies =toCurrency.split(",");
                return Double.parseDouble(rates.get(currencies[0]).toString())*units;
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
    }
}
