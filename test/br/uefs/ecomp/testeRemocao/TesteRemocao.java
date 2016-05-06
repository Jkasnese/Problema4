package br.uefs.ecomp.testeRemocao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Ponto;

public class TesteRemocao {
	
	Controller controller = new Controller();
	
	@Test
	public void testRemoverPontoSucesso(){
		
		Ponto pontoA = controller.cadastrarPonto("Garagem");
		Ponto pontoB = controller.cadastrarPonto("Rua Tal");
		Ponto pontoC = controller.cadastrarPonto("Avenida Etc");
		
		assertEquals(controller.getListaPontos().size(), 3);
		
		controller.removerPonto(pontoB);
		
		assertEquals(controller.getListaPontos().size(), 2);
		
		assertTrue(controller.getListaPontos().contains(pontoA));
		
		assertTrue(controller.getListaPontos().contains(pontoC));
		
	}
	
	
	@Test
	public void testRemoverArestaSucesso(){
		
		Ponto pontoA = controller.cadastrarPonto("Garagem");
		Ponto pontoB = controller.cadastrarPonto("Rua Etc");
		
		try {
			controller.cadastrarAresta(pontoA, pontoB, 5);
		} catch (PontoNaoExistenteException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(pontoA.getListaArestas().size(), 1);
		assertEquals(pontoB.getListaArestas().size(), 1);
		
		controller.removerAresta(pontoA, pontoB);
		
		assertTrue(pontoA.getListaArestas().isEmpty());
		assertTrue(pontoB.getListaArestas().isEmpty());
	}

}
