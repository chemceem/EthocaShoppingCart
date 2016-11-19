package com.ethoca.shoppingcart.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
/**
 * Created by Chemcee. M. C on 19-11-2016.
 */
public class AppInitializer implements WebApplicationInitializer {

    private static final String CONFIG_LOCATION = "com.ethoca.shoppingcart.config";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        System.out.println("Initializing Application for " + servletContext.getServerInfo());

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setConfigLocation(CONFIG_LOCATION);

        // Add the servlet mapping manually and make it initialize automatically
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("mvc-dispatcher", dispatcherServlet);

        servlet.addMapping("/");
        servlet.setAsyncSupported(true);
        servlet.setLoadOnStartup(1);
    }

}
