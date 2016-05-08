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
		Ponto pontoA = controller.cadastrarPonto("A",0,0);
		Ponto pontoB = controller.cadastrarPonto("B",0,0);
		Ponto pontoC = controller.cadastrarPonto("C",0,0);
		Ponto pontoD = controller.cadastrarPonto("D",0,0);
		Ponto pontoE = controller.cadastrarPonto("E",0,0);
		Ponto pontoF = controller.cadastrarPonto("F",0,0);
		
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
