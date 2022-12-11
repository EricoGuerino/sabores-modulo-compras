package com.tcc.saboresmodulocompras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.saboresmodulocompras.pojo.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	@Query("SELECT DISTINCT c FROM Compra c JOIN FETCH c.itensCompra WHERE c.idCliente = ?1 ORDER BY c.dtHrCompra DESC")
	abstract List<Compra> listarPorCliente(Integer idCliente);
	
}
