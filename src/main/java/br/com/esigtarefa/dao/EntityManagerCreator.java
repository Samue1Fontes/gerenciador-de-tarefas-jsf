package br.com.esigtarefa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerCreator {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	private EntityManagerCreator() {
	}

	public static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("ESIG-Tarefa");
			em = emf.createEntityManager();
		}
		return em;
	}

	public static void closeEntityManagerFactory() {
		if (emf != null && emf.isOpen()) {
			em.close();
			emf.close();
		}
	}
}