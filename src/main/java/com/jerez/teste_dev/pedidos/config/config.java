package com.jerez.teste_dev.pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class config {

    @Bean
    public ModelMap modelMap() {
        return new ModelMap();
    }

}
