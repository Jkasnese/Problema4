package br.uefs.ecomp.testeRemocao;

import static org.junit.Assert.*;

import org.junit.Test;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.exceptions.ArestaJaCadastradaException;
import br.uefs.ecomp.exceptions.PontoComNomeNuloException;
import br.uefs.ecomp.exceptions.PontoJaCadastradoException;
import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Ponto;

public class TesteRemocao {
	
	Controller controller = new Controller();
	
	@Test
	public void testRemoverPontoSucesso(){
		
		Ponto pontoA = null;
		try {
			pontoA = controller.cadastrarPonto("Garagem",0,0);
		} catch (PontoJaCadastradoException | PontoComNomeNuloException e) {
			e.printStackTrace();
		}
		Ponto pontoB = null;
		Ponto pontoD = null;
		Ponto pontoE = null;
		try {
			pontoB = controller.cadastrarPonto("Rua Tal",0,0);
			pontoD = controller.cadastrarPonto("Rua Ou",0,0);
			pontoE = controller.cadastrarPonto("Rua Ih",0,0);
			
		} catch (PontoJaCadastradoException | PontoComNomeNuloException e) {
			e.printStackTrace();
		}
		Ponto pontoC = null;
		try {
			pontoC = controller.cadastrarPonto("Avenida Etc",0,0);
		} catch (PontoJaCadastradoException | PontoComNomeNuloException e) {
			e.printStackTrace();
		}
		
		
		try {
				controller.cadastrarAresta(pontoB, pontoA, 4, pontoB.getNomeDoLocal()+pontoA.getNomeDoLocal());
				controller.cadastrarAresta(pontoB, pontoC, 2, pontoB.getNomeDoLocal()+pontoC.getNomeDoLocal());
				controller.cadastrarAresta(pontoB, pontoD, 2, pontoB.getNomeDoLocal()+pontoD.getNomeDoLocal());
				controller.cadastrarAresta(pontoB, pontoE, 2, pontoB.getNomeDoLocal()+pontoE.getNomeDoLocal());
				
		} catch (PontoNaoExistenteException e) {
			e.printStackTrace();
		} catch (ArestaJaCadastradaException e) {
			fail();
		}
		
		assertEquals(controller.getListaPontos().size(), 5);
		
		assertEquals(pontoB.getListaArestas().size(), 4);
		
		controller.removerPonto(pontoB);
		
		assertEquals(controller.getListaPontos().size(), 4);
		
		assertTrue(controller.getListaPontos().contains(pontoA));
		
		assertEquals(pontoB.getListaArestas().size(), 0);
				
		assertTrue(controller.getListaPontos().contains(pontoC));
		
		
		
	}
	
	
	@Test
	public void testRemoverArestaSucesso(){
		
		Ponto pontoA = null;
		try {
			pontoA = controller.cadastrarPonto("Garagem",0,0);
		} catch (PontoJaCadastradoException | PontoComNomeNuloException e) {
			e.printStackTrace();
		}
		Ponto pontoB = null;
		try {
			pontoB = controller.cadastrarPonto("Rua Etc",0,0);
		} catch (PontoJaCadastradoException | PontoComNomeNuloException e) {
			e.printStackTrace();
		}
		
		try {
			controller.cadastrarAresta(pontoA, pontoB, 5, pontoA.getNomeDoLocal()+pontoB.getNomeDoLocal());
		} catch (PontoNaoExistenteException e1) {
			e1.printStackTrace();
		} catch (ArestaJaCadastradaException e) {
			fail();
		}
		
		assertEquals(pontoA.getListaArestas().size(), 1);
		assertEquals(pontoB.getListaArestas().size(), 1);
		
		controller.removerAresta(pontoA, pontoB);
		
		assertTrue(pontoA.getListaArestas().isEmpty());
		assertTrue(pontoB.getListaArestas().isEmpty());
	}

}
