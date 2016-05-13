package br.uefs.ecomp.model;

public class Aresta {

	private Ponto pontoSeguinte;
	private int duracao;
	private String nome;

	public Aresta(Ponto pontoSeguinte, int duracao, String nome){
		this.pontoSeguinte = pontoSeguinte;
		this.duracao = duracao;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
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
