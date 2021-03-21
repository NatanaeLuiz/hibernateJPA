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
			if (aluno.getId() == 0) {
				em.persist(aluno); //PERSIST Salva objeto no banco
			} else {
				em.merge(aluno); //MERGE   atualiza objeto no banco
			}
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
			Query sql = em.createQuery("FROM Aluno aluno"); //Utilizando PQL não precisa do SELECT
			alunos = sql.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao consultar!");
			em.getTransaction().rollback();
		} finally {
			emf.close();
		}
		return alunos;
	}
	
	public Aluno getAluno(Integer id) {
		Aluno aluno = null;
		try {
			aluno = em.find(Aluno.class, id);
		} catch (Exception e) {
			System.out.println("Erro ao buscar aluno por id " + id);
		} finally {
			emf.close();
		}
		return aluno;
	}

}
