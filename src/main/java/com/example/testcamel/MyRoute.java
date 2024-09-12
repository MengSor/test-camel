package com.example.testcamel;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {
    @Autowired
    private OpenAPI openAPI;

    @Override
    public void configure() throws Exception {
//        OpenAPI openAPI = new OpenAPI();
//        Info info = new Info()
//                .title("My Api")
//                .version("1.0")
//                .description("This is my API");
//        openAPI.info(info);

        restConfiguration()
                .component("servlet")
                .contextPath("/api")
                .apiContextPath("/api-docs")
                .apiContextRouteId("api-docs")
                .apiProperty("api.title", openAPI.getInfo().getTitle())
                .apiProperty("api.version", openAPI.getInfo().getVersion())
                .apiProperty("base.path", "/api")
                .apiProperty("cors", "true")
                .apiProperty("openapi", openAPI.toString())
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
//
//        from("direct:getUser")
//                .bean(UserRepository.class , "getUser");
//
//        from("direct:save")
//                .log("Received a save request : ${body}")
//                .marshal().json()
//                .unmarshal().json(User.class)
//                .bean(UserRepository.class, "save(${body})");
//
//        from("direct:getUserById")
//                .log("Received header: ${header.id}")
//                .bean(UserRepository.class ,"getUserById(${header.id})");
//
//        from("file:message/foo")
//                .to("jms:queue:foo");

    }
}
