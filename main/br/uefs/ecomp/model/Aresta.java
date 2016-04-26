package br.uefs.ecomp.model;

public class Aresta {

	private Ponto pontoSeguinte;
	private int duracao;

	private Aresta(Ponto pontoSeguinte, int duracao){
		this.pontoSeguinte = pontoSeguinte;
		this.duracao = duracao;
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
	
}
