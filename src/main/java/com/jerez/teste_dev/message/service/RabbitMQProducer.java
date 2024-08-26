package com.jerez.teste_dev.message.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jerez.teste_dev.pedidos.entity.StatusPedido;

@Service
public class RabbitMQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${SPRING_RABBITMQ_EXCHANGE}")
    private String exchange;

    @Value("${SPRING_RABBITMQ_ROUTINGKEY}")
    private String routingkey;

    public void enviarMensagem(Long idProduto, StatusPedido status) {
        amqpTemplate.convertAndSend(exchange, routingkey, idProduto, message -> {
            message.getMessageProperties().getHeaders().put("status", status);
            return message;
        });
    }

}
