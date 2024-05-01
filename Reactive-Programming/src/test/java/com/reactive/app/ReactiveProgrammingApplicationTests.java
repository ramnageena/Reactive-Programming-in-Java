package com.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Locale;

@SpringBootTest
class ReactiveProgrammingApplicationTests {

	@Test
	void contextLoads() {
	}
@Test
	void workingWithMonoDemo(){
		//Creating Mono
		Mono<String> m1 = Mono.just("Learn Reactive Programming").log()

				.then(Mono.error(new RuntimeException("Error")));
	   // creating mono with error
	Mono<Object> error = Mono.error(new RuntimeException("error"));
	error.subscribe(System.out::println);
	m1.subscribe(d->{
				System.out.println(d.toUpperCase());
			});
	}
 @Test
	void joinMono(){
		Mono<String> m1 = Mono.just("First Mono ");

		Mono<String> m2 = Mono.just("Second Mono ");

	//concatWith()  are used to combine two mono and return Flux

	 Flux<String> stringFlux = m1.concatWith(m2);
	 stringFlux.subscribe(d->{
		 System.out.println(d);
	 });
	 System.out.println("#########################");

	 //zip()  are used to combine two mono and return Mono and its a static method
	 Mono<Tuple2<String, String>> zip = Mono.zip(m1, m2);
	  zip.subscribe(d->{
			System.out.println(d);
	        System.out.println(d.getT1());
			System.out.println(d.getT2());
});

//zipWith()  are used to combine two mono and return Mono, it's not a static method . will call on object
	 Mono<Tuple2<String, String>> zipWith = m1.zipWith(m2);
	     zipWith.subscribe(d->{
		 System.out.println(d);
		 System.out.println(d.getT1());
		 System.out.println(d.getT2());
	 });


	}

	@Test
	void impMethod(){
		/*
		1. map()Transform the item emitted by this Mono by applying a synchronous function to it
		 and always return mono
		 */
		System.out.println("_______________map()_____________");
		Mono<String> m1 = Mono.just("Learning Reactive programming language");
		Mono<String> map = m1.map(i -> i.toUpperCase());

		/*other ways
		Mono<String> map = m1.map(String::toUpperCase);
		 */
		map.subscribe(i->{
			System.out.println(i);
		});
		System.out.println("_______________flatMap()_____________");

		/*
		2. flatMap() Transform the item emitted by this Mono asynchronously,
		 returning the value emitted by another Mono (possibly changing the value type).
		 */

		Mono<String[]> flatMap = m1.flatMap(i -> Mono.just(i.split(" ")));
		flatMap.subscribe(i->{
			for(String s:i){
				System.out.println(s);
			}
		});
		System.out.println("_______________flatMapMany()_____________");

		/*
		2. flatMap() Transform the item emitted by this Mono asynchronously,
		 returning the value emitted by another Flux (possibly changing the value type).
		 and it will return FLux
		 if we want to convert mono to flux then we can go for flatMapMany()
		 */
		Flux<String> flatMapMany = m1.flatMapMany(i -> Flux.just(i.split(" ")));
		flatMapMany.subscribe(itm->{
				System.out.println(itm);
		});
	}

}
