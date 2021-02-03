package cursojava.executavel;

import javax.swing.JOptionPane;

import cursojava.classes.Aluno;
import cursojava.classes.Disciplina;

public class ArrayVetor {

	public static void main(String[] args) {
		
		 double[] notas = {8.8, 9.7, 7.6, 7.1};
		 double[] notasLogica = {8.8, 9.7, 7.6, 7.1};
		
		 Aluno aluno = new Aluno();          
		
		 aluno.setNome("Alex Fernando Egidio");
		 aluno.setNomeEscola("jdev");
		
		 
		 Disciplina disciplina = new Disciplina();
		 disciplina.setDisciplina("Curso de Java");
		 
		 
		 disciplina.setNota(notas);
		 
		 aluno.getDisciplina().add(disciplina);
		 
		 Disciplina disciplina2 = new Disciplina();
		 disciplina2.setDisciplina("Logica");
		 disciplina2.setNota(notasLogica);
		 
		 aluno.getDisciplina().add(disciplina2);
		 
		 Aluno[]arrayAlunos = new Aluno[1];
		
		 arrayAlunos[0] = aluno;
		 
		 for(int pos = 0;  pos < arrayAlunos.length; pos++) {
			 System.out.println("Nome do aluno é: " + arrayAlunos[pos].getNome());
			 
			 for(Disciplina d : arrayAlunos[pos].getDisciplina()) {
				 System.out.println("Nome da disciplina é : " + d.getDisciplina());
				 
				 for(int posnota = 0; posnota < d.getNota().length; posnota++) {
					 System.out.println("A nota numero : " + posnota + " é igual = " + d.getNota()[posnota]);
				 }
			 }
		 }
		 
	}
	
	
}
