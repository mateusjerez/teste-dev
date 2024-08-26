package com.jerez.teste_dev.message.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("pedido_queue", true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("pedido_exchange");
    }

}
