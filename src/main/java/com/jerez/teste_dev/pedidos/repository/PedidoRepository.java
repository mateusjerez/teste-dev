package com.jerez.teste_dev.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jerez.teste_dev.pedidos.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
