package com.reactive.app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class FluxService {
    // All operator examples go here

    //Creating flux

    public Flux<String> getFlux(){
        Flux<String> flux = Flux.just("Ram", "Sudhir", "Anshu", "Avinash");
        return  flux;
    }
    public  Flux<String> fruitFlux(){
        List<String> fruits = List.of("Apple", "Mango", "Banana", "Grapes");
        Flux<String> fruitsFlux = Flux.fromIterable(fruits);
        return  fruitsFlux;
    }

    //map() method

    public  Flux<String>mapExample(){
        Flux<String> fluxNamed = getFlux().map(name -> name.toUpperCase());
        return  fluxNamed;

    }
    public Flux<String>filterExample(){
        return  getFlux().filter(name-> name.length()<=5);
    }
}
