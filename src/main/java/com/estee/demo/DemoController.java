package com.estee.demo;


import com.estee.demo.config.AccessToken;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class DemoController {

    private ConsumersManagementApi api = new ConsumersManagementApi();
    private AccessToken token;

    public DemoController(AccessToken token) {
        this.token = token;
        final ApiClient apiClient = api.getApiClient();
        apiClient.setConnectTimeout(50000);
        apiClient.setWriteTimeout(50000);
        apiClient.setBasePath("http://apimelcpoc.azure-api.net/cdp");
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/get-consumers")
    public String getConsumers(@RequestParam(value = "market") String market,
                               @RequestParam(value = "firstName", required = false) String firstName,
                               @RequestParam(value = "lastName", required = false) String lastName,
                               @RequestParam(value = "brandString") String brandString,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                               @RequestParam(value = "postalCode", required = false) String postalCode,
                               Model model) throws ApiException {
        final ApiClient apiClient = api.getApiClient();
        apiClient.addDefaultHeader("Authorization", "Bearer " + this.token.getToken());

        var marketId = MarketEnum.fromValue(market);
        var brand = BrandEnum.fromValue(brandString);

        if (firstName.isBlank()) {
            firstName = null;
        }
        if (lastName.isBlank()) {
            lastName = null;
        }
        if (email.isBlank()) {
            email = null;
        }
        if (phoneNumber.isBlank()) {
            phoneNumber = null;
        }
        if (postalCode.isBlank()) {
            postalCode = null;
        }

        final List<Consumer> consumers = api.getConsumers(marketId, firstName, lastName, brand, email, phoneNumber, postalCode, 0, 100);

        model.addAttribute("consumerList", consumers);
        return "home";
    }


    @PostMapping("/get-consumer")
    public String getConsumer(@RequestParam("market") String market,
                              @RequestParam("consumerId") Long consumerId, Model model) throws ApiException {
        log.trace("attempting to get consumer");

        final ApiClient apiClient = api.getApiClient();
        apiClient.addDefaultHeader("Authorization", "Bearer " + this.token.getToken());
        var marketId = MarketEnum.fromValue(market);

        final Consumer consumer1 = api.getConsumer(marketId, consumerId);

        model.addAttribute("consumer", consumer1);

        return "home";
    }


    @PostMapping("/create-consumer")
    public String createConsumer(Consumer consumer,
                                 Model model) throws ApiException {
        log.trace("attempting to create consumer");
        final ApiClient apiClient = api.getApiClient();
        apiClient.addDefaultHeader("Authorization", "Bearer " + this.token.getToken());
        setupRequiredFieldsForTheUI(consumer);


        final Consumer consumer1 = api.createConsumer(consumer.getMarket(), consumer);


        model.addAttribute("createdConsumer", consumer1);
        return "home";
    }

    //TODO add just for ui fix
    private void setupRequiredFieldsForTheUI(Consumer consumer) {
        var phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("+19052223333;ext=555");
        consumer.setPhoneNumber(List.of(phoneNumber));
        var email = new EmailAddress();
        email.setEmailAddress("test@email.com");
        consumer.setEmail(List.of(email));
        var adres = new Address();
        adres.setCity("NY");
        adres.setCountry("USA");
        adres.setLine("2383  Burke Street");
        consumer.setAddress(List.of(adres));
        consumer.setElcMasterId(UUID.randomUUID());
    }


}
