package br.uefs.ecomp.model;

public class Aresta {

	private Ponto pontoInicial;
	private Ponto pontoFinal;
	private int duracao;

	private Aresta(Ponto pontoInicial, Ponto pontoFinal, int duracao){
		this.pontoInicial = pontoInicial;
		this.pontoFinal = pontoFinal;
		this.duracao = duracao;
	}
	
}
