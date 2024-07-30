package com.abhilash.sb.assignment_w4.advices;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class CurrencyResponse {
    private Map<String,Object> rates;

}
