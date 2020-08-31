package com.estee.demo;


import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.ConsumersManagementApi;
import org.openapitools.client.model.Address;
import org.openapitools.client.model.BrandEnum;
import org.openapitools.client.model.Consumer;
import org.openapitools.client.model.EmailAddress;
import org.openapitools.client.model.MarketEnum;
import org.openapitools.client.model.PhoneNumber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class DemoController {

    private ConsumersManagementApi api = new ConsumersManagementApi();

    public DemoController() {
        final ApiClient apiClient = api.getApiClient();
        apiClient.setConnectTimeout(50000);
        apiClient.setWriteTimeout(50000);
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/get-consumers")
    public String getConsumers(@RequestParam("market") String market,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("brandString") String brandString,
                               @RequestParam("email") String email,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("postalCode") String postalCode,
                               Model model) throws ApiException {
        var marketId = MarketEnum.fromValue(market);
        var brand = BrandEnum.fromValue(brandString);

        final List<Consumer> consumers = api.getConsumers(marketId, firstName, lastName, brand, email, phoneNumber, postalCode, 0, 100);

        model.addAttribute("consumerList", consumers);
        return "home";
    }


    @PostMapping("/get-consumer")
    public String getConsumer(@RequestParam("market") String market,
                              @RequestParam("consumerId") Long consumerId, Model model) throws ApiException {
        log.trace("attempting to get consumer");

        var marketId = MarketEnum.fromValue(market);
        final Consumer consumer1 = api.getConsumer(marketId, consumerId);

        log.info(consumer1.toString());

        model.addAttribute("consumer", consumer1);

        return "home";
    }


    @PostMapping("/create-consumer")
    public String createConsumer(Consumer consumer,
                                 Model model) throws ApiException {
        log.trace("attempting to create consumer");

        var phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("+19052223333;ext=55");
        consumer.setPhoneNumber(List.of(phoneNumber));
        var  email = new EmailAddress();
        email.setEmailAddress("test@email.com");
        consumer.setEmail(List.of(email));
        var adres = new Address();
        adres.setCity("NY");
        adres.setCountry("USA");
        adres.setLine("2383  Burke Street");
        consumer.setAddress(List.of(adres));
        consumer.setElcMasterId(UUID.randomUUID());


        final Consumer consumer1 = api.createConsumer(consumer.getMarket(), consumer);

        model.addAttribute("createdConsumer", consumer1);


        return "home";
    }


}
