import java.util.List;

public class Teste {

	public static void main(String[] args) {
		Aluno a1 = new Aluno();
		a1.setNome("Guilherme Santos");
		a1.setIdade(22);
		a1.setMatricula("123456789");
		
		Aluno a2 = new Aluno();
		a2.setNome("Guilherme Santos");
		a2.setIdade(22);
		a2.setMatricula("123456789");
		
		Aluno a3 = new Aluno();
		a3.setNome("Guilherme Santos");
		a3.setIdade(22);
		a3.setMatricula("123456789");
		
		AlunoController con = new AlunoController();
		

List<Aluno>  alunos = con.listar();
 
		for(int i = 0; i < alunos.size(); i++) {
			System.out.println("Nome:" + alunos.get(i).getNome() + " - Idade:" + alunos.get(i).getIdade()+" - Matricula: " + alunos.get(i).getMatricula());
		}
		
	}

}
