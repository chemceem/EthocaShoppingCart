package com.ethoca.shoppingcart.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Chemcee. M. C on 27-11-2016.
 */
@Configuration
@ComponentScan
public class ServerConfiguration extends WebMvcAutoConfiguration{
}
