package com.reactive.app;

import com.reactive.app.service.FluxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class FluxDemo {
    @Autowired
    private FluxService fluxService;
    @Test
    void simpleFluxTest(){
        fluxService.getFlux().subscribe(data->{
            System.out.println(data);
            System.out.println("Flux is done here");
        });
        System.out.println("________ Fruit flux____________");
        fluxService.fruitFlux().subscribe(System.out::println);

        System.out.println("______________ map flux____________");
        fluxService.mapExample().subscribe(System.out::println);

        System.out.println("______________ filter flux____________");
        fluxService.filterExample().subscribe(i->{
            System.out.println(i);
        });
    }

    @Test
    void mapUnitTesting(){
        Flux<String> stringFlux = fluxService.mapExample();
        StepVerifier.create(stringFlux).expectNextCount(4).verifyComplete();
    }
    @Test
    void filterUnitTest(){
        Flux<String> filtered = fluxService.filterExample();
        StepVerifier.create(filtered).expectNext("Ram","Anshu").verifyComplete();
    }

}
