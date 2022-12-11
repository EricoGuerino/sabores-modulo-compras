package com.tcc.saboresmodulocompras.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcc.saboresmodulocompras.pojo.Carrinho;
import com.tcc.saboresmodulocompras.pojo.Compra;
import com.tcc.saboresmodulocompras.pojo.ItemCompra;
import com.tcc.saboresmodulocompras.repositories.CarrinhoRepository;
import com.tcc.saboresmodulocompras.repositories.CompraRepository;
import com.tcc.saboresmodulocompras.repositories.ItemCompraRepository;


@Component
public class PopulaDados {
	
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	@Autowired
	private ItemCompraRepository itemCompraRepository;
	
	@PostConstruct
	public void populaDados() {
		List<Compra> compras = compraRepository.findAll();
		
		if (compras.isEmpty()) {
			Carrinho carrinho1 = new Carrinho();
			carrinho1.setComprado(Boolean.TRUE);
			carrinho1.setDtHrInclusao(new Date());
			carrinho1.setIdCliente(1);
			carrinho1.setIdProduto(1);
			carrinho1.setPreco(19.99);
			carrinho1.setQuantidade(40);
			
			Carrinho carrinho2 = new Carrinho();
			carrinho2.setComprado(Boolean.TRUE);
			carrinho2.setDtHrInclusao(new Date());
			carrinho2.setIdCliente(1);
			carrinho2.setIdProduto(2);
			carrinho2.setPreco(14.99);
			carrinho2.setQuantidade(40);
			
			Carrinho carrinho3 = new Carrinho();
			carrinho3.setComprado(Boolean.TRUE);
			carrinho3.setDtHrInclusao(new Date());
			carrinho3.setIdCliente(1);
			carrinho3.setIdProduto(3);
			carrinho3.setPreco(49.99);
			carrinho3.setQuantidade(75);
			
			Compra compra = new Compra();
			compra.setDtHrCompra(new Date());
			compra.setEntregue(Boolean.FALSE);
			compra.setIdCliente(1);
			compraRepository.save(compra);
			
			List<ItemCompra> itensCompra = new ArrayList<ItemCompra>();
			ItemCompra item1 = new ItemCompra();
			item1.setCompra(compra);
			item1.setIdProduto(1);
			item1.setQuantidade(40);
			item1.setPreco(19.99);
			
			ItemCompra item2 = new ItemCompra();
			item2.setCompra(compra);
			item2.setIdProduto(2);
			item2.setQuantidade(40);
			item2.setPreco(14.99);
			
			ItemCompra item3 = new ItemCompra();
			item3.setCompra(compra);
			item3.setIdProduto(3);
			item3.setQuantidade(75);
			item3.setPreco(49.99);
			
			itensCompra.addAll(Arrays.asList(item1,item2,item3));
			compra.setItensCompra(itensCompra);
			
			carrinhoRepository.saveAll(Arrays.asList(carrinho1,carrinho2,carrinho3));
			itemCompraRepository.saveAll(Arrays.asList(item1,item2,item3));
		}
	}
	
}
