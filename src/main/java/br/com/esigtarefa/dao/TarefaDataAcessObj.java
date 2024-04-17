package br.com.esigtarefa.dao;

import br.com.esigtarefa.model.PrioridadeTarefa;
import br.com.esigtarefa.model.ResponsavelTarefa;
import br.com.esigtarefa.model.SituacaoTarefa;
import br.com.esigtarefa.model.Tarefa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class TarefaDataAcessObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public TarefaDataAcessObj() {
		em = EntityManagerCreator.getEntityManager();
	}

	public void cadastrarTarefa(Tarefa tarefa) throws Exception {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}

		try {
			em.persist(tarefa);
			em.getTransaction().commit();
			System.out.println("Tarefa cadastrada com sucesso!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Falha ao Cadastradar tarefa!");
			throw e;

		}
	}

	public List<Tarefa> listarTarefas(String titulo, Long numero, String descricao, ResponsavelTarefa responsavel,
			SituacaoTarefa situacao, PrioridadeTarefa prioridade, Date deadline) {
		StringBuilder jpql = new StringBuilder("SELECT t FROM Tarefa t WHERE 1=1 ");
		Map<String, Object> parameters = new HashMap<>();

		if (titulo != null && !titulo.isEmpty()) {
			jpql.append("AND (t.titulo LIKE :titulo OR t.descricao LIKE :titulo) ");
			parameters.put("titulo", "%" + titulo + "%");
		}

		if (numero != null) {
			jpql.append("AND t.numero = :numero ");
			parameters.put("numero", numero);
		}

		if (descricao != null && !descricao.isEmpty()) {
			jpql.append("AND t.descricao LIKE :descricao ");
			parameters.put("descricao", "%" + descricao + "%");
		}

		if (responsavel != null) {
			jpql.append("AND t.responsavel = :responsavel ");
			parameters.put("responsavel", responsavel);
		}

		if (situacao != null) {
			jpql.append("AND t.situacao = :situacao ");
			parameters.put("situacao", situacao);
		}

		// Verifica se há algum critério de busca especificado
		if (parameters.isEmpty()) {
			return Collections.emptyList(); // Retorna uma lista vazia
		}

		TypedQuery<Tarefa> query = em.createQuery(jpql.toString(), Tarefa.class);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	public void atualizarTarefa(Tarefa tarefa) throws Exception {
		em.getTransaction().begin();

		try {
			em.merge(tarefa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	public void excluirTarefa(Tarefa tarefa) throws Exception {
		em.getTransaction().begin();

		try {
			tarefa = em.find(Tarefa.class, tarefa.getNumero());
			em.remove(tarefa);
			em.getTransaction().commit();
			System.out.println("Tarefa excluida com sucesso!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
}
