package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Aluno;

public class AlunoController {

	EntityManagerFactory emf;
	EntityManager em;
	
	public AlunoController() {
		emf = Persistence.createEntityManagerFactory("aluno"); //Mesmo parametro informado na persistence.xml (name="aluno")
		em = emf.createEntityManager();
	}
	
	public void salva(Aluno aluno) {
		em.getTransaction().begin(); //Inicia transação no banco
		em.merge(aluno); //Salva objeto no banco
		em.getTransaction().commit();
		emf.close();
	}
	
	public void remove(Aluno _aluno) {
		em.getTransaction().begin(); //Inicia transação no banco
		Query query = em.createNativeQuery("DELETE aluno FROM ALUNO WHERE matricula = " + _aluno.getMatricula());//remove objeto no banco
		query.executeUpdate();
		em.getTransaction().commit();
		emf.close();
	}
	
	public List<Aluno> lista(){
		em.getTransaction().begin();
		Query sql = em.createQuery("SELECT aluno FROM Aluno aluno");
		List<Aluno> alunos = sql.getResultList();
		emf.close();
		return alunos;
	}

}
