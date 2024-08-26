package com.jerez.teste_dev.pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerez.teste_dev.message.service.RabbitMQProducer;
import com.jerez.teste_dev.pedidos.entity.Pedido;
import com.jerez.teste_dev.pedidos.entity.StatusPedido;
import com.jerez.teste_dev.pedidos.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    public Pedido salvarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.PENDENTE);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
    
    public Optional<Pedido> buscarPedido(Long id) {
        return pedidoRepository.findById(id);
    }

    public void atualizarStatusPedido(Long id, StatusPedido statusPedido) {
        pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        rabbitMQProducer.enviarMensagem(id, statusPedido);
        // pedido.setStatus(statusPedido);

        // pedidoRepository.save(pedido);
    }
}
