package br.uefs.ecomp.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Grafo {

	private int numeroVertices;
	private int[][] matrizAdjacencia;
	private ArrayList<Ponto> listaPontos;
	
	
	public Grafo (ArrayList<Ponto> listaPontos){
		
		//Constroi a matriz de adjacencia
		this.listaPontos = listaPontos;
		this.numeroVertices = listaPontos.size();
		matrizAdjacencia = new int[numeroVertices][numeroVertices];
		
		
		// Itera a lista de pontos, modificando os valores da matriz de adjacencia de acordo com 
		// as listas de  arestas presentes dentro de ponto.
		Iterator<Ponto> iPontos = listaPontos.iterator();
		while(iPontos.hasNext()){
			// Obtem o próximo ponto da lista
			Ponto pontoAtual = iPontos.next();
			
			// Dentro de cada ponto, existe uma lista de arestas, que precisa ser percorrida ate o final
			Iterator<Aresta> iAresta = pontoAtual.getListaArestas().iterator();
			while(iAresta.hasNext()){
				// Obtem proxima aresta
				Aresta arestaAtual = iAresta.next();
				Ponto pontoSeguinteAux = arestaAtual.getPontoSeguinte();
				
				// Uma vez obtido os dois pontos da aresta, modifica-se o valor dessa aresta na matriz
				matrizAdjacencia[listaPontos.indexOf(pontoAtual)][listaPontos.indexOf(pontoSeguinteAux)] = arestaAtual.getDuracao();
			}
		}
		
	}


	public int[][] getMatrizAdjacencia() {
		return matrizAdjacencia;
	}


	/**
	 * Modifica a posicao matriz[LINHA][COLUNA] pelo valor VALOR
	 * @param valor
	 * @param linha
	 * @param coluna
	 */
	public void setValorMatriz(int valor, int linha, int coluna) {
		this.matrizAdjacencia[linha][coluna] = valor;
	}
	
}
