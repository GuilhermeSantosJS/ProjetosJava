package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String tipo;
	
	@Column(nullable = false)
	private String numero;
	
    @ManyToOne(optional = false)
	private UsuarioSpringData usuarioSpringData;
	
    
    
public UsuarioSpringData getUsuarioSpringData() {
		return usuarioSpringData;
	}

	public void setUsuarioSpringData(UsuarioSpringData usuarioSpringData) {
		this.usuarioSpringData = usuarioSpringData;
	}

	public Long getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public String getNumero() {
		return numero;
	}

public void setId(Long id) {
	this.id = id;
}

public void setNumero(String numero) {
	this.numero = numero;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

}
