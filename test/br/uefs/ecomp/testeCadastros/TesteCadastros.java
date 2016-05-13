package br.uefs.ecomp.testeCadastros;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.exceptions.PontoComNomeNuloException;
import br.uefs.ecomp.exceptions.PontoJaCadastradoException;
import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Aresta;
import br.uefs.ecomp.model.Ponto;

public class TesteCadastros {
	
	private Controller controller = new Controller();
	
	@Test
	public void testCadastrarPontoSucesso() throws PontoJaCadastradoException, PontoComNomeNuloException{
	
		Ponto ponto1 = controller.cadastrarPonto("Garagem",0,0);
		
		Ponto ponto2 = controller.cadastrarPonto("Rua Etc",0,0);
		
		Ponto ponto3 = controller.cadastrarPonto("Avenida Tal",0,0);
		
		Iterator<Ponto> itera = controller.getListaPontos().iterator();
		
		Ponto aux = itera.next();
		assertEquals(aux, ponto1);
		
		aux = itera.next();
		assertEquals(aux, ponto2);
		
		aux = itera.next();
		assertEquals(aux, ponto3);

	}
	
	@Test
	public void testCadastrarArestaSucesso() throws PontoJaCadastradoException, PontoComNomeNuloException{
		
		Ponto pontoA = controller.cadastrarPonto("Garagem",0,0);
		Ponto pontoB = controller.cadastrarPonto("Rua Etc",0,0);
		Ponto pontoC = controller.cadastrarPonto("Avenida Tal",0,0);
		
		try {
			controller.cadastrarAresta(pontoA, pontoB, 5);
		} catch (PontoNaoExistenteException e1) {
			e1.printStackTrace();
		}
		
		try {
			controller.cadastrarAresta(pontoA, pontoC, 7);
		} catch (PontoNaoExistenteException e) {
			e.printStackTrace();
		}
		
		Iterator<Aresta> itera = pontoA.getListaArestas().iterator();
		
		Aresta aux = itera.next();
		assertEquals(aux.getPontoSeguinte(), pontoB);
		
		aux = itera.next();
		assertEquals(aux.getPontoSeguinte(), pontoC);
		
		itera = pontoB.getListaArestas().iterator();
		
		aux = itera.next();
		assertEquals(aux.getPontoSeguinte(), pontoA);
		
		itera = pontoC.getListaArestas().iterator();
		
		aux = itera.next();
		assertEquals(aux.getPontoSeguinte(), pontoA);
	
	}
	
}

	
