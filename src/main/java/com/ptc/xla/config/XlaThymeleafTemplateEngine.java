package com.ptc.xla.config;

import org.thymeleaf.context.Context;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.util.Locale;
import java.util.Map;

/**
 * Created by ssinghal
 * Created on 27-Jan-2016
 * If you refactor this code, remember: Code so clean you could eat off it!
 */
public class XlaThymeleafTemplateEngine extends TemplateEngine {

    private org.thymeleaf.TemplateEngine templateEngine;

    public XlaThymeleafTemplateEngine() {
        TemplateResolver defaultTemplateResolver = new TemplateResolver();
        defaultTemplateResolver.setTemplateMode("XHTML");
        defaultTemplateResolver.setPrefix("templates/");
        defaultTemplateResolver.setSuffix(".html");
        defaultTemplateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        defaultTemplateResolver.setResourceResolver(new ClassLoaderResourceResolver());
        this.templateEngine = new org.thymeleaf.TemplateEngine();
//        this.templateEngine.addMessageResolver(new StandardMessageResolver());
        this.templateEngine.setTemplateResolver(defaultTemplateResolver);
    }

    @Override
    public String render(ModelAndView modelAndView) {
        Object model = modelAndView.getModel();
        if(model instanceof Map) {
            Map modelMap = (Map)model;
            Context context = new Context((Locale) modelMap.get("locale"));
            context.setVariables(modelMap);
            return this.templateEngine.process(modelAndView.getViewName(), context);
        } else {
            throw new IllegalArgumentException("modelAndView.getModel() must return a java.util.Map");
        }
    }
}
