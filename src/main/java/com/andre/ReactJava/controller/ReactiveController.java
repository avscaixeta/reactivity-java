package com.andre.ReactJava.controller;

import com.andre.ReactJava.entity.Person;
import com.andre.ReactJava.service.ReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

    private final ReactiveService reactiveService;

    @Autowired
    public ReactiveController(ReactiveService reactiveService) {
        this.reactiveService = reactiveService;
    }

    @GetMapping("/persons")
    Mono<Person> getPerson() {
        return reactiveService.findPerson();
    }

    @GetMapping(value = "/numbers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> getNumbers() {
        return reactiveService.getInterval();
    }

    @GetMapping(value = "/reactives")
    Flux<String> showReactiveJava() {
        return reactiveService.executeParallelMethods();
    }




}
