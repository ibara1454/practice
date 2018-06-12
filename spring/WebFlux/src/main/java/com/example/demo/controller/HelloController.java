package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class HelloController {
    @Value("${app.string}")
    private String string;

    @GetMapping(path = "/")
    Flux<String> hello() {
        return Flux.just(string);
    }

    @GetMapping(path = "/stream")
    Flux<Integer> stream() {
        return Flux.generate(
            () -> 0,
            (state, sink) -> {
                sink.next(state);
                return state + 1;
            });
    }
}
