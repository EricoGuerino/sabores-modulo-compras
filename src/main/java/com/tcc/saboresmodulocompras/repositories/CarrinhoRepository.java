package com.tcc.saboresmodulocompras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.saboresmodulocompras.pojo.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
	
	@Query("SELECT cart FROM Carrinho cart WHERE cart.idCliente = ?1 AND cart.comprado = ?2")
	abstract List<Carrinho> listarCarrinhoPorCliente(Integer idCliente, Boolean comprado);
	
	@Query("SELECT cart FROM Carrinho cart WHERE cart.idCliente = ?1 AND cart.idProduto = ?2 and comprado = ?3")
	abstract Carrinho obterCarrinho(Integer idCliente, Integer idProduto, Boolean comprado);
}
