package br.uefs.ecomp.controller;

import java.util.ArrayList;
import java.util.Iterator;

import br.uefs.ecomp.exceptions.ArestaJaCadastradaException;
import br.uefs.ecomp.exceptions.PontoComNomeNuloException;
import br.uefs.ecomp.exceptions.PontoJaCadastradoException;
import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Aresta;
import br.uefs.ecomp.model.Grafo;
import br.uefs.ecomp.model.Ponto;

public class Controller {

	private ArrayList<Ponto> listaPontos = new ArrayList<Ponto>();
	
	
	public Ponto cadastrarPonto(String nomeDoLocal, int coordX, int coordY) throws PontoJaCadastradoException, PontoComNomeNuloException{
		
		if (nomeDoLocal == null){
			throw new PontoComNomeNuloException("Tentando cadastrar ponto com nome nulo!");
		}
		
		Iterator<Ponto> itr = listaPontos.iterator();
		
		while(itr.hasNext()){
			Ponto aux = itr.next();
			if (aux.getNomeDoLocal().equals(nomeDoLocal)){
				throw new PontoJaCadastradoException("Ja existe um ponto com este nome");
			}
		}
		
		Ponto novoPonto = new Ponto(nomeDoLocal, coordX, coordY);
		
		listaPontos.add(novoPonto);
		
		return novoPonto;
	}
	
	
	public void cadastrarAresta(Ponto pontoInicial, Ponto pontoFinal, int duracao, String nome) throws PontoNaoExistenteException, ArestaJaCadastradaException{
	
		// Caso os pontos escolhidos nao estejam cadastrados, lanca excecao
		if(!listaPontos.contains(pontoInicial)) 
			throw new PontoNaoExistenteException("Ponto inicial escolhido nao foi cadastrado");
		if (!listaPontos.contains(pontoFinal)) 
			throw new PontoNaoExistenteException("Ponto final escolhido nao foi cadastrado");
		
		// Confere se aresta ja existe
		Iterator<Aresta> iPontoInicial = pontoInicial.getListaArestas().iterator();
		Aresta aux;
		while (iPontoInicial.hasNext()) {
			aux = iPontoInicial.next();
			if (aux.getPontoSeguinte() == pontoFinal){
				throw new ArestaJaCadastradaException("Voce ja cadastrou essa aresta!");
			}
		}

	
		// Cria duas novas arestas. A primeira tera seu ponto seguinte como o ponto inicial
	 	// e a segunda tem seu ponto seguinte como o ponto final, ja que o grafo nao eh direcionado
		Aresta novaAresta1 = new Aresta(pontoFinal, duracao, nome);
		Aresta novaAresta2 = new Aresta(pontoInicial, duracao, nome);
		
		// Adiciona as arestas as listas de arestas dos seus respectivos pontos 
		pontoInicial.getListaArestas().add(novaAresta1);
		pontoFinal.getListaArestas().add(novaAresta2);
	
	}
	

	public void removerPonto(Ponto ponto){
		
		Iterator<Aresta> itera = ponto.getListaArestas().iterator();
		
		// Percorre a lista de arestas do ponto a ser removido
/*		Aresta aux = null;

//		Aresta aux2;
		while (itera.hasNext()){
//			aux2 = aux;
			aux = itera.next();
//			ponto.getListaArestas().remove(aux);
			removerAresta(ponto, aux.getPontoSeguinte());
		}
		removerAresta(ponto, ponto.getListaArestas().get(0).getPontoSeguinte());
		
*/ 
		int tamanho = ponto.getListaArestas().size();
		for(int i = 0; i<tamanho; i++){
			removerAresta(ponto, ponto.getListaArestas().get(0).getPontoSeguinte());

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
				itera.remove();
				break;
			}
				
		}
		
		itera = pontoB.getListaArestas().iterator();
		
		while(itera.hasNext())
		{
			Aresta aresta = itera.next();
			if(aresta.getPontoSeguinte().equals(pontoA))
			{
				itera.remove();
				break;
			}
				
		}
	}
	
	// Busca um ponto na lista para auxiliar na remocao
	public Ponto buscarPonto(String nomeDoPonto){
		
		Ponto ponto = new Ponto("", 0, 0);
		Iterator<Ponto> iterador = listaPontos.iterator();
		while(iterador.hasNext()){
			
			ponto = iterador.next();
			if(ponto.getNomeDoLocal().equals(nomeDoPonto))
				break;
			
		}
		return ponto;
	}
	
	private int calcularRota(ArrayList<Aresta> caminho, Ponto pontoInicial, Ponto pontoFinal) throws PontoNaoExistenteException{
		
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
		
		// Encontra caminho inverso
		ArrayList<Ponto> caminhoInverso = new ArrayList();
		int i = NUMERO_DE_PONTOS - 1;
		while (i != 0){
			caminhoInverso.add(listaPontos.get(i));
			i = pontoAnterior[i];
		}
		
		ArrayList<Ponto> caminhoPontos = new ArrayList();
		// Adiciona caminho na ordem correta, removendo o último de caminhoInverso e adicionando em caminho
		while (!caminhoInverso.isEmpty()){
			caminhoPontos.add(caminhoInverso.remove(caminhoInverso.size() - 1));
		}
		
		Iterator<Ponto> itr = caminhoPontos.iterator();
		Ponto anterior = listaPontos.get(0);
		Ponto aux;
		
		// Enquanto nao percorreu todos os pontos:
		while (itr.hasNext()){
			aux = itr.next();
			Iterator<Aresta> itrAresta = anterior.getListaArestas().iterator();
			Aresta arestaAux;
			// Procura, dentro de ponto, aresta a ser adicionada
			while(itrAresta.hasNext()){
				arestaAux = itrAresta.next();
				if(arestaAux.getPontoSeguinte() == aux){
					caminho.add(arestaAux);
					break;
				}
			}
			anterior = aux;
		}
		
		return distancia[NUMERO_DE_PONTOS - 1];
	}
	
	public int calcularRota (ArrayList<Aresta> caminho, Ponto pontoInicial, Ponto pontoColeta, Ponto pontoFinal) throws PontoNaoExistenteException{
		int distancia = 0;
		distancia += calcularRota(caminho, pontoInicial, pontoColeta);
		distancia += calcularRota(caminho, pontoColeta, pontoFinal);
		return distancia;
	}
	
	public ArrayList<Ponto> getListaPontos(){
		return listaPontos;
	}
	
}
