package com.jerez.teste_dev.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jerez.teste_dev.produtos.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}