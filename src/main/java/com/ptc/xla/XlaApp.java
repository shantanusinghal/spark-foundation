package com.ptc.xla;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ptc.xla.config.WebConfig;
import com.ptc.xla.services.LoginService;

/**
 * Created by ssinghal Created on 25-Jan-2016 If you refactor this code, remember: Code so clean you
 * could eat off it!
 */
@Configuration
@ComponentScan({"com.ptc.xla"})
public class XlaApp {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(XlaApp.class);
    new WebConfig(ctx.getBean(LoginService.class));
    ctx.registerShutdownHook();
  }
}
