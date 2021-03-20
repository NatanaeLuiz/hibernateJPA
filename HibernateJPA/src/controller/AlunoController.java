package controller;

import java.util.ArrayList;
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
		try {
			em.getTransaction().begin(); //Inicia transação no banco
			//em.merge(aluno); //MERGE   Salva objeto no banco
			em.persist(aluno); //PERSIST Salva objeto no banco
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro na Gravação");
			em.getTransaction().rollback();
		} finally {
			emf.close();
		}
	}
	
	public void remove(Aluno _aluno) {
		try {
			em.getTransaction().begin(); //Inicia transação no banco
			Query query = em.createNativeQuery("DELETE aluno FROM ALUNO WHERE matricula = " + _aluno.getMatricula());//remove objeto no banco
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro ao tentar remover " + _aluno.getNome());
			em.getTransaction().rollback();
		} finally {
			emf.close();
		}
	}
	
	public List<Aluno> lista(){
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			em.getTransaction().begin();
			Query sql = em.createQuery("SELECT aluno FROM Aluno aluno");
			alunos = sql.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao consultar!");
			em.getTransaction().rollback();
		} finally {
			emf.close();
		}
		return alunos;
	}

}
