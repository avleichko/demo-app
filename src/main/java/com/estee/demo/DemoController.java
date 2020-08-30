package com.estee.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class DemoController {


    @GetMapping("/")
    public String home (){

        return "home";
    }

    @PostMapping("/get-consumers")
    public String getConsumers (@RequestParam("market") String market){
        log.warn(market);
        return "home";
    }
}
