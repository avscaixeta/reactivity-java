package com.andre.ReactJava.routers;

import com.andre.ReactJava.handlers.NumbersHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
public class RouterConfig {

    private final NumbersHandler numbersHandler;

    public RouterConfig(NumbersHandler numbersHandler) {
        this.numbersHandler = numbersHandler;
    }

    /**
        Faz o mapeamento dos endpoints
        Vantagens: Pode-se integrar ao springsecurity, aplicar regras de inicialização, desabilitar recursos com
            features flag, ou seja, mais flexibilidade para trabalhar com os endpoints.
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/num"),
                serverRequest -> ServerResponse.ok().body(Mono.just(100), Integer.class))

                .andRoute(RequestPredicates.GET("/interval"),
                        serverRequest -> numbersHandler.interval(serverRequest));
    }

}
