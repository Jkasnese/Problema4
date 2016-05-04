package br.uefs.ecomp.testeAlgoritmo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Ponto;

public class TestesAlgoritmo {

	private Controller controller = new Controller();
	
	@Test
	public void testCalcularRota(){
	
		// Cadastrando pontos
		Ponto pontoA = controller.cadastrarPonto("A");
		Ponto pontoB = controller.cadastrarPonto("B");
		Ponto pontoC = controller.cadastrarPonto("C");
		Ponto pontoD = controller.cadastrarPonto("D");
		Ponto pontoE = controller.cadastrarPonto("E");
		Ponto pontoF = controller.cadastrarPonto("F");
		
		//Cadastrando arestas
		try {
			controller.cadastrarAresta(pontoA, pontoB, 3);
			controller.cadastrarAresta(pontoA, pontoC, 2);
			controller.cadastrarAresta(pontoA, pontoD, 4);
			controller.cadastrarAresta(pontoB, pontoC, 10);
			controller.cadastrarAresta(pontoB, pontoE, 7);
			controller.cadastrarAresta(pontoC, pontoE, 6);
			controller.cadastrarAresta(pontoD, pontoE, 8);
			controller.cadastrarAresta(pontoE, pontoF, 9);
		} catch (PontoNaoExistenteException e) {
			fail();
		}
		
		
		
		// Calcula rota
		try {
			assertEquals(17, controller.calcularRota(controller.getListaPontos(), pontoA, pontoF));
		} catch (PontoNaoExistenteException e) {
			fail();
		}
	}
}
