package com.ethoca.shoppingcart.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Chemcee. M. C on 20-11-2016.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ethoca.shoppingcart.domain"})
@EnableJpaRepositories(basePackages = {"com.ethoca.shoppingcart.dao"})
public class RepositoryConfig {
}
