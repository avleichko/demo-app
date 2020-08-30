package com.estee.demo;


import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.ConsumersManagementApi;
import org.openapitools.client.model.BrandEnum;
import org.openapitools.client.model.Consumer;
import org.openapitools.client.model.MarketEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class DemoController {

    private final ConsumersManagementApi api = new ConsumersManagementApi();

    @GetMapping("/")
    public String home (){

        return "home";
    }

    @PostMapping("/get-consumer")
    public String getConsumer (@RequestParam("market") String market,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("brandString") String brandString,
                               @RequestParam("email") String email,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("postalCode") String postalCode) throws ApiException {
        log.warn(market);

        var marketId = MarketEnum.fromValue(market);
        var brand = BrandEnum.fromValue(brandString);

        final List<Consumer> consumers = api.getConsumers(marketId, firstName, lastName, brand, email, phoneNumber, postalCode, 0, 100);

        return "home";
    }
}
