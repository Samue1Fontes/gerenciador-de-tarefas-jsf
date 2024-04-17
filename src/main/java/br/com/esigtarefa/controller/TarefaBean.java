package br.com.esigtarefa.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.esigtarefa.model.PrioridadeTarefa;
import br.com.esigtarefa.model.ResponsavelTarefa;
import br.com.esigtarefa.model.SituacaoTarefa;
import br.com.esigtarefa.model.Tarefa;

import br.com.esigtarefa.dao.TarefaDataAcessObj;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class TarefaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numero;
	private String titulo;
	private String descricao;
	private ResponsavelTarefa responsavel;
	private PrioridadeTarefa prioridade;
	private SituacaoTarefa situacao;
	private Date deadline;

	@Inject
	private TarefaDataAcessObj tarefaDAO;

	@Inject
	private Tarefa tarefa;

	private List<Tarefa> tarefas;

	public TarefaBean() {
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo.strip();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.strip();
	}

	public ResponsavelTarefa[] getResponsaveis() {
		return ResponsavelTarefa.values();
	}

	public ResponsavelTarefa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ResponsavelTarefa responsavel) {
		this.responsavel = responsavel;
	}

	public PrioridadeTarefa[] getPrioridades() {
		return PrioridadeTarefa.values();
	}

	public PrioridadeTarefa getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeTarefa prioridade) {
		this.prioridade = prioridade;
	}

	public SituacaoTarefa[] getSituacoes() {
		return SituacaoTarefa.values();
	}

	public SituacaoTarefa getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoTarefa situacao) {
		this.situacao = situacao;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void cadastrarTarefa() throws Exception {
		setSituacao(SituacaoTarefa.EM_ANDAMENTO);
		Tarefa tarefa = new Tarefa(titulo, descricao, responsavel, situacao, prioridade, deadline);
		tarefaDAO.cadastrarTarefa(tarefa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Tarefa cadastrada com sucesso!"));
		limparCampos();
	}

	public void listarTarefas() {
		try {
			tarefas = tarefaDAO.listarTarefas(titulo, numero, descricao, responsavel, situacao, prioridade, deadline);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao listar tarefas", "Ocorreu um erro ao listar as tarefas."));
		}
	}

	public void abrirDialogEditar(Tarefa tarefa) {
		this.tarefa.setNumero(tarefa.getNumero());
		this.tarefa.setTitulo(tarefa.getTitulo());
		this.tarefa.setDescricao(tarefa.getDescricao());
		this.tarefa.setResponsavel(tarefa.getResponsavel());
		this.tarefa.setPrioridade(tarefa.getPrioridade());
		this.tarefa.setSituacao(tarefa.getSituacao());
		this.tarefa.setDeadline(tarefa.getDeadline());
	}

	public void salvarEdicaoTarefa() throws Exception {
		tarefaDAO.atualizarTarefa(tarefa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Tarefa editada com sucesso!"));
		listarTarefas();
	}

	public void excluirTarefa(Tarefa tarefa) throws Exception {
		tarefaDAO.excluirTarefa(tarefa);
		listarTarefas();
		limparCampos();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Tarefa excluida com sucesso!"));
	}

	public void concluirTarefa(Tarefa tarefa) throws Exception {
		tarefa.setSituacao(SituacaoTarefa.CONCLUIDA);
		tarefaDAO.atualizarTarefa(tarefa);
		listarTarefas();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Tarefa concluída com sucesso!"));
	}

	public void limparCampos() {
		titulo = null;
		descricao = null;
		responsavel = null;
		prioridade = null;
		deadline = null;
	}
}