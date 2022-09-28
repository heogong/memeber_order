//package com.idus.work.member.router;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.ServerResponse;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.web.servlet.function.RequestPredicates.accept;
//import static org.springframework.web.servlet.function.RouterFunctions.route;
//
//@RequiredArgsConstructor
//@Component
//public class MemberRouter {
//
//    private final MemberHandler memberHandler;
//
//    @Bean
//    public RouterFunction<ServerResponse> studentRouterFunction () {
//
//        return route()
//                .GET("/member", accept(APPLICATION_JSON), memberHandler::)
//    }
//}
