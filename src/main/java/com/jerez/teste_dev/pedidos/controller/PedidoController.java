package com.jerez.teste_dev.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerez.teste_dev.pedidos.entity.Pedido;
import com.jerez.teste_dev.pedidos.entity.StatusPedido;
import com.jerez.teste_dev.pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido){
        return pedidoService.salvarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id){
        return pedidoService.buscarPedido(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatusPedido(@PathVariable Long id, @RequestBody StatusPedido statusPedido){
        pedidoService.atualizarStatusPedido(id, statusPedido);
        return ResponseEntity.ok().build();
    }
    
}
