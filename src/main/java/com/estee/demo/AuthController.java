package com.estee.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AuthController {


    @Autowired
    WebClient  webClient;


    @GetMapping("/auth")
    public Mono<AuthResponseDto> auth(){
        return  webClient.post()
                .uri("/oauth2/v2.0/token")
                .body(BodyInserters.fromFormData("grant_type","client_credentials")
                        .with("client_id","f5d3340a-a384-49b9-a967-bfa427aa0b54")
                        .with("scope","api://8c25bda7-73a6-4775-9f31-71f9c1b2db2e/.default")
                        .with("client_secret","VRyd_QJfy39oWVBSEtev2-f4Um~_kcreAZ"))
                .retrieve()
                .bodyToMono(AuthResponseDto.class);

    }
}
