package cursojava.executavel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import cursojava.classes.Aluno;
import cursojava.classes.Diretor;
import cursojava.classes.Disciplina;
import cursojava.classes.Secretario;
import cursojava.classesauxiliares.FuncaoAutenticacao;
import cursojava.constantes.StatusAluno;
import cursojava.excecao.ExcecaoProcessarNota;
import cursojava.interfaces.PermitirAcesso;


public class PrimeiraClasseJava {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
	
		
		try {
			
		
		String login = JOptionPane.showInputDialog("Informe o login");
		String senha = JOptionPane.showInputDialog("Informe a senha");
		
		
		
		
	
		if(new FuncaoAutenticacao(new Diretor(login, senha)).autenticar()) {
			
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		HashMap<String, List<Aluno>> maps = new HashMap<String, List<Aluno>>();
		
	
		
		for(int qtd = 1; qtd <= 1; qtd++) {
			
		

		String nome = JOptionPane.showInputDialog("Qual o nome do aluno " + qtd +"?");
		String idade = JOptionPane.showInputDialog("Qual a idade ?");
		/*String dataNascimento = JOptionPane.showInputDialog("Qual a data de nascimento?");
		String rg = JOptionPane.showInputDialog("Qual o RG?");
		String cpf = JOptionPane.showInputDialog("Qual é o cpf?");
		String mae = JOptionPane.showInputDialog("Qual é o nome da mae?");
		String pai = JOptionPane.showInputDialog("Qual o nome do Pai?");
		String matricula = JOptionPane.showInputDialog("Qual e a data da matricula?");
		String serie = JOptionPane.showInputDialog("Qual a serie matriculada?");
		String escola = JOptionPane.showInputDialog("Qual a escola?");
		String ano = JOptionPane.showInputDialog("Qual o ano da matricula?");*/
		
		
		
		Aluno aluno1 = new Aluno();
		
		aluno1.setNome(nome);
		aluno1.setIdade(Integer.valueOf(idade));
		/*aluno1.setDataNascimento(dataNascimento);
		aluno1.setRegistroGeral(rg);
		aluno1.setNumeroCpf(cpf);
		aluno1.setNomeMae(mae);
		aluno1.setNomePai(pai);
		aluno1.setDataMatricula(matricula);
		aluno1.setSerieMatriculado(serie);
		aluno1.setAnoMatricula(ano);
		aluno1.setNomeEscola(escola);*/
		
		
		
		for(int pos = 1; pos <= 1; pos++) {
			String nomeDisciplina = JOptionPane.showInputDialog("Nome da Disciplina " + pos + "?");
			String notaDisciplina = JOptionPane.showInputDialog("Nota da Disciplina " + pos + "?");
			
			
			Disciplina disciplina = new Disciplina();
			disciplina.setDisciplina(nomeDisciplina);
			//disciplina.setNota(Double.valueOf(notaDisciplina));
			
			aluno1.getDisciplina().add(disciplina);
		}
		
		 int escolha = JOptionPane.showConfirmDialog(null, "Deseja remover alguma disciplina?");
		
		 
		if(escolha == 0) {
			
			int continuarRemover = 0;
			int posicao = 1;
			
			while(continuarRemover == 0) {
			String disciplinaRemover = JOptionPane.showInputDialog("Qual a disciplina 1, 2, 3, ou 4 ?");
			aluno1.getDisciplina().remove(Integer.valueOf(disciplinaRemover).intValue() - posicao);
			posicao ++;
			continuarRemover = JOptionPane.showConfirmDialog(null, "Continuar a remover?");
			}
		}
		
		alunos.add(aluno1);
	}
		
		maps.put(StatusAluno.APROVADO, new ArrayList<Aluno>());
		maps.put(StatusAluno.REPROVADO, new ArrayList<Aluno>());
		maps.put(StatusAluno.RECUPERACAO, new ArrayList<Aluno>());
		
		for (Aluno aluno : alunos) {
			
			if(aluno.getAlunoAprovado2().equalsIgnoreCase(StatusAluno.APROVADO)) {
				maps.get(StatusAluno.APROVADO).add(aluno);
			}else if (aluno.getAlunoAprovado2().equalsIgnoreCase(StatusAluno.RECUPERACAO)) {
				maps.get(StatusAluno.REPROVADO).add(aluno);
			}else if (aluno.getAlunoAprovado2().equalsIgnoreCase(StatusAluno.REPROVADO)) {
				maps.get(StatusAluno.RECUPERACAO).add(aluno);
			}
		}
	
		System.out.println("---------------Lista dos Aprovados--------------");
		for (Aluno aluno : maps.get(StatusAluno.APROVADO)) {
			System.out.println("Resultado = " + aluno.getAlunoAprovado2() + "com média de = " + aluno.getMediaNota());
			
		}
		
		System.out.println("---------------Lista dos Reprovados--------------");
		for (Aluno aluno : maps.get(StatusAluno.REPROVADO)) {
			System.out.println("Resultado = " + aluno.getAlunoAprovado2() + "com média de = " + aluno.getMediaNota());
			
		}
		
		System.out.println("---------------Lista dos alunos de Recuperacao --------------");
		for (Aluno aluno : maps.get(StatusAluno.RECUPERACAO)) {
			System.out.println("Resultado = " + aluno.getAlunoAprovado2() + "com média de = " + aluno.getMediaNota());
			
		}
 
	}else {
		JOptionPane.showConfirmDialog(null, "Acesso não permitido");
	}
 
		}catch(NumberFormatException e) {
			
			StringBuilder  saida = new StringBuilder();
			
			e.printStackTrace();
			
			System.out.println(" Mensagem: " + e.getMessage());
			
			for(int pos = 0; pos < e.getStackTrace().length; pos++) {
				saida.append("\n Classe de erro: " + e.getStackTrace()[pos].getClassName()); 
				saida.append("\n Metodo de erro: " + e.getStackTrace()[pos].getMethodName());
			    saida.append("\n Linha de erro: " + e.getStackTrace()[pos].getLineNumber()); 
			    saida.append("\n Class : " + e.getClass().getName()); 
			}
			
			
			JOptionPane.showMessageDialog(null, "Erro de conversão de numero" + saida.toString());
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Null pointer Exception" + e.getClass());
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro inesperado" + e.getClass());
		}finally {
			JOptionPane.showMessageDialog(null, "Obrigado por aprender Java");
		}
	}
	
	public static void lerArquivo () throws FileNotFoundException  {
            
		 File fil = new File("arquivo.txt");
		 Scanner scanner = new Scanner(fil);
	
	}
}

