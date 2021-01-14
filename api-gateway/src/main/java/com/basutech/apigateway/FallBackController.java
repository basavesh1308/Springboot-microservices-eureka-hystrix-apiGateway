package com.basutech.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class FallBackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallback(){
        return Mono.just("Order service is down or taking too long to respond. Please try again");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String> paymentServiceFallback(){

        return Mono.just("payment service is down or taking too long to respond. Please try again");


    }

}
