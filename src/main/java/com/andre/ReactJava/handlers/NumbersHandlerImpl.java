package com.andre.ReactJava.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.time.Duration;
import java.util.Iterator;
import java.util.stream.Stream;

@Service
public class NumbersHandlerImpl implements NumbersHandler {

    @Override
    public Mono<ServerResponse> interval(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(getInterval(), String.class);
    }

    private Flux<String> getInterval() {
        Stream<Integer> values = Stream.iterate(0, i -> i+1).limit(1000);
        Iterator<Integer> iterator = values.iterator();

        return Flux.interval(Duration.ofSeconds(5)).map(i -> iterator.next().toString()).
                doOnTerminate(() -> System.out.println("Imprimindo...")).
                log();
    }
}
