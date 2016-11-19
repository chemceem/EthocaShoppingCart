package com.ethoca.shoppingcart.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Chemcee. M. C on 19-11-2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.ethoca.shoppingcart")
public class AppConfig  extends WebMvcConfigurerAdapter {

}
