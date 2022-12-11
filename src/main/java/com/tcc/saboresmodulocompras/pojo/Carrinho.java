package com.tcc.saboresmodulocompras.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Carrinho implements Serializable {
	
	private static final long serialVersionUID = -8074886301871921700L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer idCliente;
	private Integer idProduto;
	private Double preco;
	private Integer quantidade;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHrInclusao;
	private Boolean comprado;
	
	public Carrinho() {}
	public Carrinho(Integer idProduto, Integer idCliente, Integer quantidade, Double preco) {
		setIdProduto(idProduto);
		setIdCliente(idCliente);
		setQuantidade(quantidade);
		setPreco(preco);
		setDtHrInclusao(new Date());
		setComprado(Boolean.FALSE);
	}

	public Long getId() 			{ return id; 			}
	public Integer getIdCliente() 	{ return idCliente; 	}
	public Integer getIdProduto() 	{ return idProduto; 	}
	public Double getPreco() 		{ return preco;			}
	public Integer getQuantidade() 	{ return quantidade; 	}
	public Date getDtHrInclusao() 	{ return dtHrInclusao; 	}
	public Boolean getComprado() 	{ return comprado; 		}

	public void setId(Long id) 						{ this.id = id; 					}
	public void setIdCliente(Integer idCliente) 	{ this.idCliente = idCliente; 		}
	public void setIdProduto(Integer idProduto) 	{ this.idProduto = idProduto; 		}
	public void setPreco(Double preco) 				{ this.preco = preco; 				}
	public void setQuantidade(Integer quantidade) 	{ this.quantidade = quantidade; 	}
	public void setDtHrInclusao(Date dtHrInclusao) 	{ this.dtHrInclusao = dtHrInclusao; }
	public void setComprado(Boolean comprado) 		{ this.comprado = comprado; 		}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		return Objects.equals(id, other.id);
	}

	








	
	
}
