package com.tcc.saboresmodulocompras.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Compra implements Serializable {
	
	private static final long serialVersionUID = 7714871822781554117L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer idCliente;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHrCompra;
	private Boolean entregue;
	@OneToMany(mappedBy = "compra")
	private List<ItemCompra> itensCompra;
	
	public Compra() {}
	
	public Long getId() 						{ return id; 			}
	public Integer getIdCliente() 				{ return idCliente; 	}
	public Date getDtHrCompra() 				{ return dtHrCompra; 	}
	public Boolean getEntregue() 				{ return entregue;		}
	public List<ItemCompra> getItensCompra() 	{ return itensCompra; 	}
	
	public void setId(Long id) 									{ this.id = id; 					}
	public void setIdCliente(Integer idCliente) 				{ this.idCliente = idCliente; 		}
	public void setDtHrCompra(Date dtHrCompra) 					{ this.dtHrCompra = dtHrCompra; 	}
	public void setEntregue(Boolean entregue) 					{ this.entregue = entregue;			}
	public void setItensCompra(List<ItemCompra> itensCompra) 	{ this.itensCompra = itensCompra; 	}
	
	@JsonProperty("dataFmt")
	public String getDataFmt() {
		if (this.dtHrCompra != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("PT", "BR"));
			return df.format(getDtHrCompra());
		}
		return "";
	}
	@JsonProperty("horaFmt")
	public String getHoraFmt() {
		if (this.dtHrCompra != null) {
			DateFormat df = new SimpleDateFormat("HH:mm", new Locale("PT", "BR"));
			return df.format(getDtHrCompra());
		}
		return "";
	}
	
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
		Compra other = (Compra) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
