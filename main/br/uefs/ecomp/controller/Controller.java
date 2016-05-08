package br.uefs.ecomp.controller;

import java.util.ArrayList;
import java.util.Iterator;

import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Aresta;
import br.uefs.ecomp.model.Grafo;
import br.uefs.ecomp.model.Ponto;

public class Controller {

	private ArrayList<Ponto> listaPontos = new ArrayList<Ponto>();
	
	
	public Ponto cadastrarPonto(String nomeDoLocal, int coordX, int coordY){
		
		Ponto novoPonto = new Ponto(nomeDoLocal, coordX, coordY);
		
		listaPontos.add(novoPonto);
		
		return novoPonto;
	}
	
	
	public void cadastrarAresta(Ponto pontoInicial, Ponto pontoFinal, int duracao) throws PontoNaoExistenteException{
	
		// Caso os pontos escolhidos n�o estejam cadastrados, lan�a exce��o
		if(!listaPontos.contains(pontoInicial))
			throw new PontoNaoExistenteException("Ponto inicial escolhido n�o foi cadastrado");
		else if(!listaPontos.contains(pontoFinal))
			throw new PontoNaoExistenteException("Ponto final escolhido n�o foi cadastrado");
		
		else{
				// Cria duas novas arestas. A primeira ter� seu ponto seguinte como o ponto inicial
			 	// e a segunda tem seu ponto seguinte como o ponto final, j� que o grafo n�o � direcionado
				Aresta novaAresta1 = new Aresta(pontoFinal, duracao);
				Aresta novaAresta2 = new Aresta(pontoInicial, duracao);
				
				// Adiciona as arestas �s listas de arestas dos seus respectivos pontos 
				pontoInicial.getListaArestas().add(novaAresta1);
				pontoFinal.getListaArestas().add(novaAresta2);
		}
	
	}
	
	public void removerPonto(Ponto ponto){
		
		Iterator<Aresta> itera = ponto.getListaArestas().iterator();
		// Itera a lista de arestas do ponto a ser removido
		while(itera.hasNext()){
			
			// Econtra o ponto seguinte de cada aresta da lista e 
			// chama o m�todo de remo��o de arestas, passando os v�rtices da
			// aresta a ser removida
			Aresta aresta = itera.next();
			Ponto pontoSeguinte = aresta.getPontoSeguinte();
			
			removerAresta(ponto, pontoSeguinte);
		}
		
		listaPontos.remove(ponto);
	}
	
	public void removerAresta(Ponto pontoA, Ponto pontoB){
		
		Iterator<Aresta> itera = pontoA.getListaArestas().iterator();
		
		while(itera.hasNext())
		{
			Aresta aresta = itera.next();
			if(aresta.getPontoSeguinte().equals(pontoB))
			{
				pontoA.getListaArestas().remove(aresta);
				break;
			}
				
		}
		
		itera = pontoB.getListaArestas().iterator();
		
		while(itera.hasNext())
		{
			Aresta aresta = itera.next();
			if(aresta.getPontoSeguinte().equals(pontoA))
			{
				pontoB.getListaArestas().remove(aresta);
				break;
			}
				
		}
	}
	
	public int calcularRota(ArrayList<Ponto> listaPontos, Ponto pontoInicial, Ponto pontoFinal) throws PontoNaoExistenteException{
		
		final int NUMERO_DE_PONTOS = listaPontos.size(); 
		
		// Remove o ponto inicial da lista de pontos e adiciona-o na primeira posição
		if (!listaPontos.remove(pontoInicial)){ // Caso ponto inicial nao conste na lista
			throw new PontoNaoExistenteException("Ponto inicial nao foi cadastrado!");
		}
		listaPontos.add(0, pontoInicial);
		
		// Remove ponto final da lista de pontos e insere-o no final
		if (!listaPontos.remove(pontoFinal)){
			throw new PontoNaoExistenteException("Ponto final nao foi cadastrado!");
		}
		listaPontos.add(pontoFinal);
		
		
		// Constroi grafo
		Grafo grafo = new Grafo(listaPontos);
		
		
		// Inicio do Algoritmo de Dijkstra
		
		final  int VALOR_MAXIMO = Integer.MAX_VALUE/2; // Maior valor de int possivel
		int[][] matriz = grafo.getMatrizAdjacencia(); // Recebe a matriz de adjacencia do grafo
		int[] distancia = new int[NUMERO_DE_PONTOS]; // Array que guarda a distancia de um ponto para outro
		boolean[] visitado = new boolean[NUMERO_DE_PONTOS]; // Array que diz se ponto ja foi visitado ou nao 
		int[] pontoAnterior = new int[NUMERO_DE_PONTOS]; // Array que registra o caminho
		
		
		
		// Primeiro, inicializa-se todas as arrays.
		
		for (int i=0; i<NUMERO_DE_PONTOS; i++){
			
			visitado[i] = false;
			pontoAnterior[i] = 0;
			
			for (int j=0; j<NUMERO_DE_PONTOS; j++){
				if(matriz[i][j] == 0)
					matriz[i][j] = VALOR_MAXIMO; 
			}
		}
		
		distancia = matriz[0]; // As distancias do ponto inicial para os pontos seguintes ja estao prontas!
		visitado[0] = true; // O ponto inicial ja foi visitado, pois sao conhecidas suas distancias
		
		
		
		// Depois, começa o calculo das distancias
		
		int distanciaMinima; // distancia minima parcial
		int proximoPonto = 0;
		
		for (int i=0; i<NUMERO_DE_PONTOS; i++){
			
			distanciaMinima = VALOR_MAXIMO;
			
			// Obtem proximo ponto que sera visitado.
			// Proximo ponto visitado sera aquele com a menor distancia total
			for(int j = 0; j<NUMERO_DE_PONTOS; j++){
				
				if(distanciaMinima > distancia[j] && (!(visitado[j]))){
					distanciaMinima = distancia[j];
					proximoPonto = j;
				}
				
			}
			
			
			visitado[proximoPonto] = true;
			
			// A partir do ponto obtido anteriormente, visita todos os outros pontos.
			for (int k = 0; k<NUMERO_DE_PONTOS; k++){
				if(!(visitado[k])){ // Se K nao ja tiver sido visitado
					if(distanciaMinima + matriz[proximoPonto][k] < distancia[k]){
						distancia[k] = distanciaMinima + matriz[proximoPonto][k];
						pontoAnterior[k] = proximoPonto;
					}
				}
			}
			
			// Caso ja tenha chegado no ponto final, pare.
			if (visitado[NUMERO_DE_PONTOS - 1]){ 
				break;
			}
			
			
		}
		return distancia[NUMERO_DE_PONTOS - 1];
	}
	
	public ArrayList<Ponto> getListaPontos(){
		return listaPontos;
	}
	
}
