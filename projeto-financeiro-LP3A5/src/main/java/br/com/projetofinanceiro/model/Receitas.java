package br.com.projetofinanceiro.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Receitas")
public class Receitas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idReceitas;
	
	@NotNull
	@Size(min = 1, max = 300)
	private String descricaoReceita;
	
	@NotNull
	private double salarioMensal;
	
	@NotNull
	private double transporteMensal;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataReceita;
	
	@NotNull
	private double valeAlimentacao;
	
	@NotNull
	private double outrasValores;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Pessoa pessoa;
	
	private double totalGanhos;
	
	public double getTotalGanhos() {
		
		return totalGanhos = salarioMensal + valeAlimentacao +
				transporteMensal + outrasValores;
		
		
		
	}
	
	public void setTotalGanhos(double totalGanhos) {
		this.totalGanhos = totalGanhos;
	}

	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(Date dataReceita) {
		this.dataReceita = dataReceita;
	}

	public double getOutrasValores() {
		return outrasValores;
	}

	public void setOutrasValores(double outrasValores) {
		this.outrasValores = outrasValores;
	}

	public long getIdReceitas() {
		return idReceitas;
	}

	public void setIdReceitas(long idReceitas) {
		this.idReceitas = idReceitas;
	}

	public double getSalarioMensal() {
		return salarioMensal;
	}

	public void setSalarioMensal(double salarioMensal) {
		this.salarioMensal = salarioMensal;
	}

	public double getTransporteMensal() {
		return transporteMensal;
	}

	public void setTransporteMensal(double transporteMensal) {
		this.transporteMensal = transporteMensal;
	}

	public double getValeAlimentacao() {
		return valeAlimentacao;
	}

	public void setValeAlimentacao(double valeAlimentacao) {
		this.valeAlimentacao = valeAlimentacao;
	}

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}
	

	
	
	
	
}
