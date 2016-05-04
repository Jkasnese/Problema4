package br.uefs.ecomp.model;

import java.util.ArrayList;

public class Ponto {

	private String nomeDoLocal;
	private ArrayList<Aresta> listaArestas;
	
	public Ponto(String nomeDoLocal){
		this.nomeDoLocal = nomeDoLocal;
	}
	
	public String getNomeDoLocal() {
		return nomeDoLocal;
	}
	public void setNomeDoLocal(String nomeDoLocal) {
		this.nomeDoLocal = nomeDoLocal;
	}
	public ArrayList<Aresta> getListaArestas() {
		return listaArestas;
	}
	public void setListaArestas(ArrayList<Aresta> listaArestas) {
		this.listaArestas = listaArestas;
	}
	
}
