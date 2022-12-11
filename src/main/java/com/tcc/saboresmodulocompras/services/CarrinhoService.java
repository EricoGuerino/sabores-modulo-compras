package com.tcc.saboresmodulocompras.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.saboresmodulocompras.pojo.Carrinho;
import com.tcc.saboresmodulocompras.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public List<Carrinho> listarCarrinhoPorCliente(Integer idCliente) {
		return carrinhoRepository.listarCarrinhoPorCliente(idCliente, Boolean.FALSE);
	}

	public Carrinho obterPeloId(Long id) {
		return carrinhoRepository.findById(id).get();
	}

	public Carrinho insert(Map<String, Object> mapCarrinho) {
		
		Integer idProduto = (Integer)mapCarrinho.get("idProduto");
		Integer idCliente = (Integer)mapCarrinho.get("idCliente");
		Integer quantidade = (Integer)mapCarrinho.get("quantidade");
		Double preco = (Double)mapCarrinho.get("preco");
		Carrinho carrinho = null;
		Carrinho item = carrinhoRepository.obterCarrinho(idCliente, idProduto, Boolean.FALSE);
		if (item != null) {
			item.setQuantidade(item.getQuantidade()+1);
			carrinhoRepository.save(item);
			carrinho = item;
		} else {
			carrinho = new Carrinho(idProduto, idCliente, quantidade, preco);
			carrinhoRepository.save(carrinho);
		}
		
		return carrinho;
	}
	
	public void delete(Long id) {
		carrinhoRepository.deleteById(id);
	}
	
}
