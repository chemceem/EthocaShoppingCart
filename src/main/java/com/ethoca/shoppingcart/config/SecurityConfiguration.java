package com.ethoca.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Chemcee. M. C on 19-11-2016.
 * Configuration Class for spring security
 */

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception
    {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
    }
}