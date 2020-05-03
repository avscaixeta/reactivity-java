package com.andre.ReactJava.service;

import com.andre.ReactJava.entity.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
public class ReactiveService {

    /**
     * Ciclo de vida
     * OnNext() --> Notify the Observer about new flux
     * onCompleted() --> When not exists more data to send
     * onError()
     *
     * doOnTerminate --> register an action to call just before an Observable terminates, either successfully or with an error
     * @return
     */


    public Mono<Person> findPerson() {
        return Mono.fromSupplier(() -> new Person("André", "Caixeta"))
                .doOnTerminate(() -> System.out.println("Process terminated."))
                .log();
    }

    public Flux<String> executeParallelMethods() {
        return Flux.merge(showReactiveJava(), getInterval())
                .parallel()
                .runOn(Schedulers.elastic()).sequential();
    }

    public Mono<String> showReactiveJava() {
        System.out.println(getInterval());
        return Mono.fromSupplier(() -> "Retornou antes mesmo de terminar a execução do método de intervalo");
    }

    public Flux<String> getInterval() {
        Stream<Integer> values = Stream.iterate(0, i -> i+1).limit(1000);
        Iterator<Integer> iterator = values.iterator();

        return Flux.interval(Duration.ofSeconds(5)).map(i -> iterator.next().toString()).
                doOnTerminate(() -> System.out.println("Imprimindo...")).
                log();
    }


}
