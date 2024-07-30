package com.abhilash.sb.assignment_w4.controllers;

import com.abhilash.sb.assignment_w4.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convertCurrency")
@RequiredArgsConstructor
public class currencyController {

    private final CurrencyService currencyService;
    @GetMapping("{baseCurrency}/{currency}/{amount}")
    public Double getConvertedCurrency(@PathVariable String baseCurrency,@PathVariable String currency,@PathVariable Double amount){
        return currencyService.convertCurrency(baseCurrency,currency,amount);
    }
}
