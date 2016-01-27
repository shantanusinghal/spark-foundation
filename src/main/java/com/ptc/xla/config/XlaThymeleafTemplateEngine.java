package com.ptc.xla.config;

import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.TemplateResolver;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.util.Locale;
import java.util.Map;

/**
 * Created by ssinghal
 * Created on 27-Jan-2016
 */
public class XlaThymeleafTemplateEngine extends TemplateEngine {

    private org.thymeleaf.TemplateEngine templateEngine;

    public XlaThymeleafTemplateEngine(XlaMessageResolver messageResolver, TemplateResolver templateResolver) {
        this.templateEngine = new org.thymeleaf.TemplateEngine();
        this.templateEngine.addMessageResolver(messageResolver);
        this.templateEngine.setTemplateResolver(templateResolver);
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
