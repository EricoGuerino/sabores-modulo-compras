package com.tcc.saboresmodulocompras.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.saboresmodulocompras.pojo.Carrinho;
import com.tcc.saboresmodulocompras.services.CarrinhoService;
import com.tcc.saboresmodulocompras.utils.Util;

@RestController
@RequestMapping(value = "/carrinho")
public class CarrinhoResource {
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@RequestMapping(value = "/cliente/{idCliente}", method = RequestMethod.GET)
	public ResponseEntity<?> listarCompras(@PathVariable("idCliente") Integer idCliente) {
		List<Carrinho> carrinho = carrinhoService.listarCarrinhoPorCliente(idCliente);
		return Util.buildResponse(HttpStatus.OK).body(carrinho);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable("id")Long id) throws Exception {
		Carrinho carrinho = carrinhoService.obterPeloId(id);
		return Util.buildResponse(HttpStatus.OK).body(carrinho);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Map<String,Object> mapCarrinho) {
		Carrinho carrinho = null; 
		Map<String,Object> retorno = new HashMap<String,Object>();
		try {
			carrinho = carrinhoService.insert(mapCarrinho);
			retorno.put("mensagem", "");
			retorno.put("ok", true);
			retorno.put("carrinho", carrinho);
			return Util.buildResponse(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			retorno.put("mensagem", e.getMessage());
			retorno.put("ok", false);
			return Util.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerDoCarrinho(@PathVariable("id")Long id) throws Exception {
		Map<String,Object> retorno = new HashMap<String,Object>();
		try {
			carrinhoService.delete(id);
			retorno.put("ok", true);
			return Util.buildResponse(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			retorno.put("mensagem", e.getMessage());
			retorno.put("ok", false);
			return Util.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
}
