package com.estee.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class Config {

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl("https://login.microsoftonline.com/b41b72d0-4e9f-4c26-8a69-f949f367c91d")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION,"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6ImppYk5ia0ZTU2JteFBZck45Q0ZxUms0SzRndyJ9.eyJhdWQiOiI4YzI1YmRhNy03M2E2LTQ3NzUtOWYzMS03MWY5YzFiMmRiMmUiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vYjQxYjcyZDAtNGU5Zi00YzI2LThhNjktZjk0OWYzNjdjOTFkL3YyLjAiLCJpYXQiOjE1OTg4ODU4NTQsIm5iZiI6MTU5ODg4NTg1NCwiZXhwIjoxNTk4ODg5NzU0LCJhaW8iOiJFMkJnWUpqVWFYdUp2LzJ2WUxSZDZRdXhxd21lQUE9PSIsImF6cCI6ImY1ZDMzNDBhLWEzODQtNDliOS1hOTY3LWJmYTQyN2FhMGI1NCIsImF6cGFjciI6IjEiLCJvaWQiOiI1OGQ3ZDBmNy0xODY1LTQ4ZmItOGQ2Ni0wOGE5MjQ0NGYxZDQiLCJyaCI6IjAuQUFBQTBISWJ0SjlPSmt5S2FmbEo4MmZKSFFvMDBfV0VvN2xKcVdlX3BDZXFDMVFKQUFBLiIsInN1YiI6IjU4ZDdkMGY3LTE4NjUtNDhmYi04ZDY2LTA4YTkyNDQ0ZjFkNCIsInRpZCI6ImI0MWI3MmQwLTRlOWYtNGMyNi04YTY5LWY5NDlmMzY3YzkxZCIsInV0aSI6IjZKbUJyYlI0U2t5dFgwbXFyMVNWQUEiLCJ2ZXIiOiIyLjAifQ.S6Ujen1_yHK7tIN2n2t3H9VdIx9VGgfUl5ROnEjiotgVL7bdjo6RekHmtFRq1Uhr4sEeOoWC9jVpR1IeZtO0i-I8fPr4WMM99fFZ_DOEBjTzGOP_EtB6hRvGfQmaKk80PVuFR-eayXPXKJjiYF_ceQ3_VZY-izaGYX76rzaFkYlK53b1oXgEzPiJFhM65DU8CE8lcGq39wX4fNEKUy33HLtf3VgYBw-iEVlB6wVy86okqLBk9WyzU9G89uJasLbhrupPIc2gm4XbWeS_GtqUL2MvFNQmfoqD4f6VD1cIecuHNewDlMSLoTYH5fhPPcltYejLe2G9ZBWGSwSKX6G3oA")
                .defaultHeader(HttpHeaders.COOKIE,"x-ms-gateway-slice=estsfd; stsservicecookie=estsfd; fpc=AiEsmO4xdedLrdQ9mfa_R0Wunq0uAgAAAAU14NYOAAAA")
                .defaultUriVariables(Collections.singletonMap("url", "https://login.microsoftonline.com/b41b72d0-4e9f-4c26-8a69-f949f367c91d"))
                .build();
    }
}
