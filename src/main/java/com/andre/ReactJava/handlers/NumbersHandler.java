package com.andre.ReactJava.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface NumbersHandler {

    Mono<ServerResponse> interval(ServerRequest serverRequest);
}
