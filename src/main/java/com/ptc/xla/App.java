package com.ptc.xla;

import com.ptc.xla.config.WebConfig;
import com.ptc.xla.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ssinghal
 * Created on 25-Jan-2016
 * If you refactor this code, remember: Code so clean you could eat off it!
 */
@Configuration
@ComponentScan({ "com.ptc.xla" })
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        UserService userService = ctx.getBean(UserService.class);
        new WebConfig(userService).setupRoutes();
        ctx.registerShutdownHook();
    }

}
