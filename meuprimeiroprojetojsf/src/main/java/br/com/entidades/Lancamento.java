package br.com.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lancamento implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String numeroNotaFiscal;
	
	private String empresaOrigem;
	
	private String empresaDestino;

	
	@ManyToOne(optional = false)
	@org.hibernate.annotations.ForeignKey(name="usuario_fk")
	private Pessoa usuario;
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresaDestino == null) ? 0 : empresaDestino.hashCode());
		result = prime * result + ((empresaOrigem == null) ? 0 : empresaOrigem.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroNotaFiscal == null) ? 0 : numeroNotaFiscal.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (empresaDestino == null) {
			if (other.empresaDestino != null)
				return false;
		} else if (!empresaDestino.equals(other.empresaDestino))
			return false;
		if (empresaOrigem == null) {
			if (other.empresaOrigem != null)
				return false;
		} else if (!empresaOrigem.equals(other.empresaOrigem))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroNotaFiscal == null) {
			if (other.numeroNotaFiscal != null)
				return false;
		} else if (!numeroNotaFiscal.equals(other.numeroNotaFiscal))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
	

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getEmpresaOrigem() {
		return empresaOrigem;
	}

	public void setEmpresaOrigem(String empresaOrigem) {
		this.empresaOrigem = empresaOrigem;
	}

	public String getEmpresaDestino() {
		return empresaDestino;
	}

	public void setEmpresaDestino(String empresaDestino) {
		this.empresaDestino = empresaDestino;
	}
	
	
	
	
	
	
}
