package com.example.testcamel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .contextPath("/api")
                .port(8080)
                .bindingMode(RestBindingMode.json);

        rest("/users")
                .get("")
                .routeId("Get")
                .to("direct:getUser")
                .get("/{id}")
                .routeId("Get By Id")
                .to("direct:getUserById")
                .post("")
                .routeId("Post")
                .to("direct:save");

        from("direct:getUser")
                .bean(UserRepository.class , "getUser");

        from("direct:save")
                .log("Received a save request : ${body}")
                .marshal().json()
                .unmarshal().json(User.class)
                .bean(UserRepository.class, "save(${body})");

        from("direct:getUserById")
                .log("Received header: ${header.id}")
                .bean(UserRepository.class ,"getUserById(${header.id})");

    }
}
