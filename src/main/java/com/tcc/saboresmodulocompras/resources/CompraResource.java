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

import com.tcc.saboresmodulocompras.pojo.Compra;
import com.tcc.saboresmodulocompras.services.CompraService;
import com.tcc.saboresmodulocompras.utils.Util;

@RestController
@RequestMapping(value = "/compras")
public class CompraResource {
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listarCompras() {
		List<Compra> compras = compraService.listarTodos();
		return Util.buildResponse(HttpStatus.OK).body(compras);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable("id")Long id) throws Exception {
		Compra compra = compraService.obterPeloId(id);
		return Util.buildResponse(HttpStatus.OK).body(compra);
	}
	
	@RequestMapping(value = "/cliente/{idCliente}", method = RequestMethod.GET)
	public ResponseEntity<?> listarPorCliente(@PathVariable("idCliente")Integer id) throws Exception {
		List<Compra> compra = compraService.listarPorCliente(id);
		return Util.buildResponse(HttpStatus.OK).body(compra);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Map<String,Object> mapCompra) {
		Compra compra = null; 
		Map<String,Object> retorno = new HashMap<String,Object>();
		try {
			compra = compraService.insert(mapCompra);
			retorno.put("mensagem", "");
			retorno.put("ok", true);
			retorno.put("compra", compra);
			return Util.buildResponse(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			retorno.put("mensagem", e.getMessage());
			retorno.put("ok", false);
			return Util.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
	
	@RequestMapping(value = "entregue/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarEntregue(@PathVariable("id") Long id) {
		Map<String,Object> retorno = new HashMap<String,Object>();
		try {
			Compra compra = compraService.alterarEntregue(id);
			retorno.put("mensagem", "");
			retorno.put("ok", true);
			retorno.put("compra", compra);
			return Util.buildResponse(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			retorno.put("mensagem", e.getMessage());
			retorno.put("ok", false);
			return Util.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
	
}
