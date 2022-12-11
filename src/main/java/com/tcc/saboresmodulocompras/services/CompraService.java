package com.tcc.saboresmodulocompras.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.saboresmodulocompras.pojo.Carrinho;
import com.tcc.saboresmodulocompras.pojo.Compra;
import com.tcc.saboresmodulocompras.pojo.ItemCompra;
import com.tcc.saboresmodulocompras.repositories.CarrinhoRepository;
import com.tcc.saboresmodulocompras.repositories.CompraRepository;
import com.tcc.saboresmodulocompras.repositories.ItemCompraRepository;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ItemCompraRepository itemCompraRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public List<Compra> listarTodos() {
		return compraRepository.findAll();
	}

	public Compra obterPeloId(Long id) {
		Optional<Compra> compra = compraRepository.findById(id);
		return compra.get();
	}

	public List<Compra> listarPorCliente(Integer id) {
		return compraRepository.listarPorCliente(id);
	}
	
	@SuppressWarnings("unchecked")
	public Compra insert(Map<String, Object> mapCompra) throws Exception {
		try {
			Integer idCliente = (Integer)mapCompra.get("idCliente");
			List<Map<String,Object>> carrinho = ((List<Map<String,Object>>)mapCompra.get("carrinho"));
			List<ItemCompra> itensCompra = new ArrayList<ItemCompra>();
			ItemCompra item = null;
			Compra compra = new Compra();
			compra.setDtHrCompra(new Date());
			compra.setIdCliente(idCliente);
			compra.setEntregue(Boolean.FALSE);
			compraRepository.save(compra);
			for (Map<String, Object> mp : carrinho) {
				Long idCarrinho = Long.valueOf((String)mp.get("id"));
				Integer quantidade = (Integer)mp.get("quantidade");
				Optional<Carrinho> _cart = carrinhoRepository.findById(idCarrinho);
				Carrinho cart = _cart.get();
				cart.setQuantidade(quantidade);
				item = new ItemCompra();
				item.setCompra(compra);
				item.setIdProduto(cart.getIdProduto());
				item.setQuantidade(cart.getQuantidade());
				item.setPreco(cart.getPreco());
				itemCompraRepository.save(item);
				itensCompra.add(item);
				
				cart.setComprado(Boolean.TRUE);
				
				carrinhoRepository.save(cart);
			}
			compra.setItensCompra(itensCompra);
			return compra;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Compra alterarEntregue(Long id) {
		Compra compra = obterPeloId(id);
		compra.setEntregue(!compra.getEntregue());
		Compra compraAlterada = compraRepository.save(compra);
		return compraAlterada;
	}

	
}
