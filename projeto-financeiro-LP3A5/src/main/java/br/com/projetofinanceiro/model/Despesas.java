package br.com.projetofinanceiro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Despesas")
public class Despesas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idDespesa;
	
	@Column(nullable = false, length = 250)
	@Size(min = 1, max = 250)
	private String descricaoDespesa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataDespesa;
	
	@NotNull
	private double contaLuz;
	
	@NotNull
	private double contaAgua;
	
	@NotNull
	private double moradiaDespesa;
	
	@NotNull
	private double alimentacaoDespesa;
	
	@NotNull
	private double internetDespesa;
	
	@NotNull
	private double outrosGastos;
	
	@ManyToOne
	@JoinColumn(name = "id",nullable = false)
	private Pessoa pessoa;
	
	private double totalGastos;

	public double getTotalGastos() {
		return totalGastos = outrosGastos + internetDespesa +
				alimentacaoDespesa + moradiaDespesa + 
				contaLuz + contaAgua;
	}

	public void setTotalGastos(double totalGastos) {
		this.totalGastos = totalGastos;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public long getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(long idDespesa) {
		this.idDespesa = idDespesa;
	}

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	
	public Date getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public double getOutrosGastos() {
		return outrosGastos;
	}

	public void setOutrosGastos(double outrosGastos) {
		this.outrosGastos = outrosGastos;
	}

	public double getContaLuz() {
		return contaLuz;
	}

	public void setContaLuz(double contaLuz) {
		this.contaLuz = contaLuz;
	}

	public double getContaAgua() {
		return contaAgua;
	}

	public void setContaAgua(double contaAgua) {
		this.contaAgua = contaAgua;
	}

	public double getMoradiaDespesa() {
		return moradiaDespesa;
	}

	public void setMoradiaDespesa(double moradiaDespesa) {
		this.moradiaDespesa = moradiaDespesa;
	}

	public double getAlimentacaoDespesa() {
		return alimentacaoDespesa;
	}

	public void setAlimentacaoDespesa(double alimentacaoDespesa) {
		this.alimentacaoDespesa = alimentacaoDespesa;
	}

	public double getInternetDespesa() {
		return internetDespesa;
	}

	public void setInternetDespesa(double internetDespesa) {
		this.internetDespesa = internetDespesa;
	}
	
	
	
	
	
	
}
