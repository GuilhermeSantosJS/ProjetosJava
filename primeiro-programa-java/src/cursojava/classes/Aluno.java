package cursojava.classes;

import java.util.ArrayList;
import java.util.List;

import cursojava.constantes.StatusAluno;

/* Está é nossa classe/objeto que representa o Aluno*/
public class Aluno extends Pessoa {

	
   private String dataMatricula;
   private String nomeEscola;
   private String anoMatricula;
   private String serieMatriculado;
   private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

public void setDisciplina(List<Disciplina> disciplina) {
	this.disciplinas = disciplina;
}

public List<Disciplina> getDisciplina() {
	return disciplinas;
}




public Aluno() {
	
}


public Aluno(String nomePadrao) {
      nome = nomePadrao;
}

public Aluno(String nomePadrao, int idadePadrao) {
	nome = nomePadrao;
	idade = idadePadrao;
}


public void setNome(String nome) {
	this.nome = nome;
}

public String getNome() {
	return nome;
}

public int getIdade() {
	return idade;
}

public void setIdade(int idade) {
	this.idade = idade;
}

public String getDataNascimento() {
	return dataNascimento;
}

public void setDataNascimento(String dataNascimento) {
	this.dataNascimento = dataNascimento;
}

public String getRegistroGeral() {
	return registroGeral;
}

public void setRegistroGeral(String registroGeral) {
	this.registroGeral = registroGeral;
}

public String getNumeroCpf() {
	return numeroCpf;
}

public void setNumeroCpf(String numeroCpf) {
	this.numeroCpf = numeroCpf;
}

public String getNomeMae() {
	return nomeMae;
}

public void setNomeMae(String nomeMae) {
	this.nomeMae = nomeMae;
}

public String getNomePai() {
	return nomePai;
}

public void setNomePai(String nomePai) {
	this.nomePai = nomePai;
}

public String getDataMatricula() {
	return dataMatricula;
}

public void setDataMatricula(String dataMatricula) {
	this.dataMatricula = dataMatricula;
}

public String getNomeEscola() {
	return nomeEscola;
}

public void setNomeEscola(String nomeEscola) {
	this.nomeEscola = nomeEscola;
}

public String getAnoMatricula() {
	return anoMatricula;
}

public void setAnoMatricula(String anoMatricula) {
	this.anoMatricula = anoMatricula;
}

public String getSerieMatriculado() {
	return serieMatriculado;
}

public void setSerieMatriculado(String serieMatriculado) {
	this.serieMatriculado = serieMatriculado;
}



public double getMediaNota() {
	
	double somaNotas = 0.0;
	
	for(Disciplina disciplina : disciplinas) {
		somaNotas += disciplina.getMediaNotas();
	}
	return somaNotas / disciplinas.size();
}



public boolean getAlunoAprovado() {
	double media = this.getMediaNota();
	if(media >= 70) {
		return true;
	}else {
		return false;
	}
	
}

public String  getAlunoAprovado2() {
	double media = this.getMediaNota();
	if(media >= 50) {
		if(media >= 70) {
			return StatusAluno.APROVADO;
		}else {
			return StatusAluno.RECUPERACAO;
		}
	}else {
		return StatusAluno.REPROVADO;
	}
	
}



@Override
public String toString() {
	return "Aluno [nome=" + nome + ", idade=" + idade + ", dataNascimento=" + dataNascimento + ", registroGeral="
			+ registroGeral + ", numeroCpf=" + numeroCpf + ", nomeMae=" + nomeMae + ", nomePai=" + nomePai
			+ ", dataMatricula=" + dataMatricula + ", nomeEscola=" + nomeEscola + ", anoMatricula=" + anoMatricula
			+ ", serieMatriculado=" + serieMatriculado + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((anoMatricula == null) ? 0 : anoMatricula.hashCode());
	result = prime * result + ((dataMatricula == null) ? 0 : dataMatricula.hashCode());
	result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
	result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());
	result = prime * result + idade;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((nomeEscola == null) ? 0 : nomeEscola.hashCode());
	result = prime * result + ((nomeMae == null) ? 0 : nomeMae.hashCode());
	result = prime * result + ((nomePai == null) ? 0 : nomePai.hashCode());
	result = prime * result + ((numeroCpf == null) ? 0 : numeroCpf.hashCode());
	result = prime * result + ((registroGeral == null) ? 0 : registroGeral.hashCode());
	result = prime * result + ((serieMatriculado == null) ? 0 : serieMatriculado.hashCode());
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
	Aluno other = (Aluno) obj;
	if (anoMatricula == null) {
		if (other.anoMatricula != null)
			return false;
	} else if (!anoMatricula.equals(other.anoMatricula))
		return false;
	if (dataMatricula == null) {
		if (other.dataMatricula != null)
			return false;
	} else if (!dataMatricula.equals(other.dataMatricula))
		return false;
	if (dataNascimento == null) {
		if (other.dataNascimento != null)
			return false;
	} else if (!dataNascimento.equals(other.dataNascimento))
		return false;
	if (disciplinas == null) {
		if (other.disciplinas != null)
			return false;
	} else if (!disciplinas.equals(other.disciplinas))
		return false;
	if (idade != other.idade)
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (nomeEscola == null) {
		if (other.nomeEscola != null)
			return false;
	} else if (!nomeEscola.equals(other.nomeEscola))
		return false;
	if (nomeMae == null) {
		if (other.nomeMae != null)
			return false;
	} else if (!nomeMae.equals(other.nomeMae))
		return false;
	if (nomePai == null) {
		if (other.nomePai != null)
			return false;
	} else if (!nomePai.equals(other.nomePai))
		return false;
	if (numeroCpf == null) {
		if (other.numeroCpf != null)
			return false;
	} else if (!numeroCpf.equals(other.numeroCpf))
		return false;
	if (registroGeral == null) {
		if (other.registroGeral != null)
			return false;
	} else if (!registroGeral.equals(other.registroGeral))
		return false;
	if (serieMatriculado == null) {
		if (other.serieMatriculado != null)
			return false;
	} else if (!serieMatriculado.equals(other.serieMatriculado))
		return false;
	return true;
}


@Override
	public boolean pessoaMaiorIdade() {
		return idade >= 21;
	}

  
   public String msgMaiorIdade(){
	   return this.pessoaMaiorIdade() ? "Oba aluno é maior de idade" : "Ixii vc é menor de idade";
   }

@Override
public double salario() {
	
	return 3900.78;
}










}
