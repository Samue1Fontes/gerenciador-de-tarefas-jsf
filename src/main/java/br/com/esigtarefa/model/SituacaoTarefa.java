package br.com.esigtarefa.model;

public enum SituacaoTarefa {
	EM_ANDAMENTO("Em andamento"),
	CONCLUIDA("Concluída");

	private String situacao;

	SituacaoTarefa(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}
}