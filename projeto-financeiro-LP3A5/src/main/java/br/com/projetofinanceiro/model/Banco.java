package br.com.projetofinanceiro.model;

public enum Banco {

	
	ITAU("Banco Itau"),
	SANTANDER("Banco Santander"),
	NUBANK("Banco Nubank"),
	INTER("Banco Inter"),
	BANCO_DO_BRASIL("Banco do Brasil"),
	CAIXA("CAIXA"),
	BRADESCO("Banco Bradesco");
	
	
	
	private String nome;
	private String valor;
	
	private Banco(String nome) {
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
