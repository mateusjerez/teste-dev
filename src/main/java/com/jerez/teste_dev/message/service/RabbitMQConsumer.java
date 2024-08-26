package com.jerez.teste_dev.message.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerez.teste_dev.pedidos.entity.Pedido;
import com.jerez.teste_dev.pedidos.repository.PedidoRepository;

@Service
public class RabbitMQConsumer {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "${SPRING_RABBITMQ_QUEUE}")
    public void consumirMensagem(Pedido pedido) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(pedido.getId());
        if (pedidoExistente.isPresent()) {
            Pedido pedidoAtualizado = pedidoExistente.get();
            pedidoAtualizado.setStatus(pedido.getStatus());
            pedidoRepository.save(pedidoAtualizado);
        }
    }
}
