package br.com.projetofinanceiro.model;

public enum Conta {

	CONTA_POUPANCA("Conta Corrente"),
	CONTA_CORRENTE("Conta Poupança");
	
	private String nome;
	private String valor;
	
	private Conta(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getValor() {
		return valor = this.name();
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
	
}
