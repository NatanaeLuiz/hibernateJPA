package view;

import java.util.List;

import controller.AlunoController;
import model.Aluno;

public class Teste {
	public static void main(String[] args) {
		Aluno a1 = new Aluno();
		a1.setNome("Natanael");
		a1.setIdade(23);
		a1.setMatricula("001");
		
		Aluno a2 = new Aluno();
		a2.setNome("Amanda");
		a2.setIdade(23);
		a2.setMatricula("002");
		
		Aluno a3 = new Aluno();
		a3.setNome("Gabriel");
		a3.setIdade(20);
		a3.setMatricula("003");
		
		Aluno a4 = new Aluno();
		a4.setNome("Bruno");
		a4.setIdade(25);
		a4.setMatricula("004");
		
		//Instancia Controller
		AlunoController con = new AlunoController();
		
		//GRAVA ALUNOS
		con.salva(a1);
		con.salva(a2);
		con.salva(a3);
		con.salva(a4);
		
		//ATUALIZA ALUNO COM ID
		Aluno alunoAtualizado = new Aluno();
		alunoAtualizado = con.getAluno(59); //Busca Aluno por Id
		alunoAtualizado.setNome("Nome Atualizado");
		con.salva(alunoAtualizado);
				
		//REMOVE ALUNO
//		con.remove(a3);
		
		//CONSULTA ALUNOS
		List<Aluno> alunos = con.lista();
		
//		for (int i = 0; i < alunos.size(); i++) {
//			System.out.println("Nome: " + alunos.get(i).getNome() + " Idade: " + alunos.get(i).getIdade() + " Matricula: " + alunos.get(i).getMatricula());
//		}
		
		for (Aluno aluno : alunos) {
			System.out.println("Nome: " + aluno.getNome() + " Idade: " + aluno.getIdade() + " Matricula: " + aluno.getMatricula());
		}
	}
}
