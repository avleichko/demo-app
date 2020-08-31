package com.estee.demo;


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

    private static final String ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6ImppYk5ia0ZTU2JteFBZck45Q0ZxUms0SzRndyJ9.eyJhdWQiOiI4YzI1YmRhNy03M2E2LTQ3NzUtOWYzMS03MWY5YzFiMmRiMmUiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vYjQxYjcyZDAtNGU5Zi00YzI2LThhNjktZjk0OWYzNjdjOTFkL3YyLjAiLCJpYXQiOjE1OTg4Njc1NzgsIm5iZiI6MTU5ODg2NzU3OCwiZXhwIjoxNTk4ODcxNDc4LCJhaW8iOiJFMkJnWUVocFhyRDExTjFxeDh0WDFMYTBLLzlPQlFBPSIsImF6cCI6ImY1ZDMzNDBhLWEzODQtNDliOS1hOTY3LWJmYTQyN2FhMGI1NCIsImF6cGFjciI6IjEiLCJvaWQiOiI1OGQ3ZDBmNy0xODY1LTQ4ZmItOGQ2Ni0wOGE5MjQ0NGYxZDQiLCJyaCI6IjAuQUFBQTBISWJ0SjlPSmt5S2FmbEo4MmZKSFFvMDBfV0VvN2xKcVdlX3BDZXFDMVFKQUFBLiIsInN1YiI6IjU4ZDdkMGY3LTE4NjUtNDhmYi04ZDY2LTA4YTkyNDQ0ZjFkNCIsInRpZCI6ImI0MWI3MmQwLTRlOWYtNGMyNi04YTY5LWY5NDlmMzY3YzkxZCIsInV0aSI6IndnVE5UY0NEMGtHZnU5Sm9ncGo5QUEiLCJ2ZXIiOiIyLjAifQ.XpYC_P7bDIqBAfuhasYpWvmIob8ORmenwoqQJJuRueFMyj_1yz3nEle3yrkm1a9y8mHYuAVos9d1ga5jHq7JFHhucBtQ25FbBJYYPSLa7oAp6n7JrZBS0kAlghZVY53KslwRCWBeRJ8jwpZ8-sTV48Jtovrvnmy4X959-wXH2tfqEsblckIEUoBO8IRsMjBkf90U2Fr03IdmZlGl30RXliIXEGkosV8PhJItd90YKv-nYF0cEMO9ogifW_PT6Rzl1L2pWPFd6xsrdY1Wb69Bc8gTTQPsCPFtXAk6T0hjxlwDGqdadnLpcCTXrDELJcJGCUOlMxra9UVvbH_Cis-WNg";

    public DemoController() {
        final ApiClient apiClient = api.getApiClient();
        apiClient.setConnectTimeout(50000);
        apiClient.setWriteTimeout(50000);
        apiClient.setBasePath("http://apimelcpoc.azure-api.net/cdp");
        apiClient.addDefaultHeader("Authorization", "Bearer " + ACCESS_TOKEN);

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
        phoneNumber.setPhoneNumber("+19052223333;ext=555");
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
