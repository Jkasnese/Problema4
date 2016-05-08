package br.uefs.ecomp.model;

import java.util.ArrayList;

public class Ponto {

	private String nomeDoLocal;
	private ArrayList<Aresta> listaArestas;
	private int coordX;
	private int coordY;
	
	public Ponto(String nomeDoLocal, int coordX, int coordY){
		this.nomeDoLocal = nomeDoLocal;
		this.coordX = coordX;
		this.coordY = coordY;
		listaArestas = new ArrayList<Aresta>();
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

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
}
