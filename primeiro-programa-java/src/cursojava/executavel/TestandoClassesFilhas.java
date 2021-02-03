package cursojava.executavel;

import cursojava.classes.Aluno;
import cursojava.classes.Diretor;
import cursojava.classes.Pessoa;
import cursojava.classes.Secretario;

public class TestandoClassesFilhas {

	public static void main(String[] args) {

		Aluno aluno = new Aluno();
		aluno.setNome("Guilherme");
		aluno.setIdade(22);
		
		Diretor diretor = new Diretor();
		diretor.setRegistroGeral("5dg4d5g4d55d4g");
		diretor.setNome("Egidio");
		diretor.setIdade(50);
		
		Secretario secretario = new Secretario();
		secretario.setExperiencia("Técnico");
		secretario.setNumeroCpf("d54gd54g5h5f1");
		secretario.setIdade(18);
		
		
		System.out.println(aluno);
		System.out.println(diretor);
		System.out.println(secretario);
		
		System.out.println(aluno.pessoaMaiorIdade() + "-" + aluno.msgMaiorIdade());
		System.out.println(diretor.pessoaMaiorIdade());
		System.out.println(secretario.pessoaMaiorIdade());
		
		
		System.out.println("Salario Aluno  é =" + aluno.salario());
		System.out.println("Salario Diretor  é =" + diretor.salario());
		System.out.println("Salario Secretario é =" + secretario.salario());
		
	  teste(aluno);
	  teste(diretor);
	  teste(secretario);
	
}
	
	
	public static void teste(Pessoa pessoa) {
		System.out.println("Essa pessoa é demais = " + pessoa.getNome() + "e o salario é de = " + pessoa.salario());
	}
}
