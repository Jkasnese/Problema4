package br.uefs.ecomp.model;

public class Aresta {

	private static int idClasse = 1;
	private Ponto pontoSeguinte;
	private int duracao, id;

	public Aresta(Ponto pontoSeguinte, int duracao){
		this.pontoSeguinte = pontoSeguinte;
		this.duracao = duracao;
		this.setId(idClasse);
		idClasse++;
	}
	
	public Ponto getPontoSeguinte() {
		return pontoSeguinte;
	}

	public void setPontoSeguinte(Ponto pontoSeguinte) {
		this.pontoSeguinte = pontoSeguinte;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
