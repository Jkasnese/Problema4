package br.uefs.ecomp.testeAlgoritmo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.exceptions.ArestaJaCadastradaException;
import br.uefs.ecomp.exceptions.PontoComNomeNuloException;
import br.uefs.ecomp.exceptions.PontoJaCadastradoException;
import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Aresta;
import br.uefs.ecomp.model.Ponto;

public class TestesAlgoritmo {

	private Controller controller = new Controller();
	
	@Test
	public void testCalcularRota(){
	
		// Cadastrando pontos
		Ponto pontoA = null;
		Ponto pontoB = null;
		Ponto pontoC = null;
		Ponto pontoD = null;
		Ponto pontoE = null;
		Ponto pontoF = null;
		try {
			pontoA = controller.cadastrarPonto("A",0,0);
			pontoB = controller.cadastrarPonto("B",0,0);
			pontoC = controller.cadastrarPonto("C",0,0);
			pontoD = controller.cadastrarPonto("D",0,0);
			pontoE = controller.cadastrarPonto("E",0,0);
			pontoF = controller.cadastrarPonto("F",0,0);
		} catch (PontoJaCadastradoException | PontoComNomeNuloException e1) {
			fail();
		}

		
		//Cadastrando arestas
		try {
			controller.cadastrarAresta(pontoA, pontoB, 3, "AB");
			controller.cadastrarAresta(pontoA, pontoC, 2, "AC");
			controller.cadastrarAresta(pontoA, pontoD, 4, "AD");
			controller.cadastrarAresta(pontoB, pontoC, 10, "BC");
			controller.cadastrarAresta(pontoB, pontoE, 7, "BE");
			controller.cadastrarAresta(pontoC, pontoE, 6, "CE");
			controller.cadastrarAresta(pontoD, pontoE, 8, "DE");
			controller.cadastrarAresta(pontoE, pontoF, 9, "EF");
		} catch (PontoNaoExistenteException e) {
			fail();
		} catch (ArestaJaCadastradaException e) {
			fail();
		}
		
		
		
		// Calcula rota
		ArrayList<Aresta> caminho = new ArrayList();
		try {
			assertEquals(17, controller.calcularRota(caminho, pontoA, pontoC, pontoF));
		} catch (PontoNaoExistenteException e) {
			fail();
		}

		Iterator<Aresta> i = caminho.iterator();
		Aresta aux;
		while (i.hasNext()){
			aux = i.next();
			System.out.println(aux.getPontoSeguinte().getNomeDoLocal());
		}
		
		
	}
}
