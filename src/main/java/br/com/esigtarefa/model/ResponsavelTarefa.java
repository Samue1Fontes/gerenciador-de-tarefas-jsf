package br.com.esigtarefa.model;

public enum ResponsavelTarefa {
    SAMUEL("Samuel Fontes"),
    VIRGILIO("Virgílio Araújo"),
    THOMAZ("Thomaz Dantas"),
    LUIS("Luis Rodrigo");
	

    private String responsavel;

    ResponsavelTarefa(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getResponsavel() {
        return responsavel;
    }
}