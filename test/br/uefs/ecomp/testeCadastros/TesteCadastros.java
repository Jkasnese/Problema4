package br.uefs.ecomp.testeCadastros;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.model.Ponto;

public class TesteCadastros {

	private Controller controller = new Controller();
	
	@Test
	public void testCadastrarPontoSucesso(){
	
		Ponto ponto1 = controller.cadastrarPonto("Garagem");
		
		Ponto ponto2 = controller.cadastrarPonto("Rua Etc");
		
		Ponto ponto3 = controller.cadastrarPonto("Avenida Tal");
		
		Iterator itera = controller.getListaPontos().iterator();
		
		Ponto aux = (Ponto) itera.next();
		assertEquals(aux, ponto1);
		
		aux = (Ponto) itera.next();
		assertEquals
		
	}
	
}
