package com.ptc.xla.config;

import com.ptc.xla.models.User;
import com.ptc.xla.services.UserService;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import spark.ModelAndView;
import spark.Request;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by ssinghal
 * Created on 25-Jan-2016
 */
public class WebConfig {

    private TemplateEngine engine;
    private UserService userService;

    public WebConfig(UserService userService) {
        this.userService = userService;
        XlaMessageResolver messageResolver = new XlaMessageResolver();
        TemplateResolver templateResolver = new TemplateResolver();
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setResourceResolver(new ClassLoaderResourceResolver());
        engine = new XlaThymeleafTemplateEngine(messageResolver, templateResolver);
    }

    public void setupRoutes() {

        get("/hello", (req, res) -> {
            User user = userService.getUser("123");
            return "Hello " + user.getUserName() + "!";
        });

        get("/eng", (req, res) -> {
            Map<String, Object> model = getModel(req);
            return new ModelAndView(model, "login");
        }, engine);

        get("/fra", (req, res) -> {
            Map<String, Object> model = getModel(req);
            return new ModelAndView(model, "login");
        }, engine);

    }

    private Map<String, Object> getModel(Request req) {
        Map<String, Object> model = new HashMap<>();
        Locale locale = req.session().attribute("user-locale");
        model.put("locale", locale == null ? Locale.ENGLISH : locale);
        return model;
    }
}
