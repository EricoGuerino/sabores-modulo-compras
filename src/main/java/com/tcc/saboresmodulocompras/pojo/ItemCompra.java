package com.tcc.saboresmodulocompras.pojo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemCompra implements Serializable {
	
	private static final long serialVersionUID = 1981908520527220129L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer idProduto;
	private Double preco;
	private Integer quantidade;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_compra", referencedColumnName = "id")
	private Compra compra;
	
	public ItemCompra() {}

	public Long getId() 			{ return id; 			}
	public Integer getIdProduto() 	{ return idProduto; 	}
	public Double getPreco() 		{ return preco; 		}
	public Integer getQuantidade() 	{ return quantidade; 	}
	public Compra getCompra() 		{ return compra;		}
	
	public void setId(Long id) 						{ this.id = id; 				}
	public void setIdProduto(Integer idProduto) 	{ this.idProduto = idProduto; 	}
	public void setPreco(Double preco) 				{ this.preco = preco; 			}
	public void setQuantidade(Integer quantidade) 	{ this.quantidade = quantidade; }
	public void setCompra(Compra compra) 			{ this.compra = compra; 		}

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
		ItemCompra other = (ItemCompra) obj;
		return Objects.equals(id, other.id);
	}
}
