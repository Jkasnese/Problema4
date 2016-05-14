package br.uefs.ecomp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.exceptions.ArestaJaCadastradaException;
import br.uefs.ecomp.exceptions.PontoComNomeNuloException;
import br.uefs.ecomp.exceptions.PontoJaCadastradoException;
import br.uefs.ecomp.exceptions.PontoNaoExistenteException;
import br.uefs.ecomp.model.Aresta;
import br.uefs.ecomp.model.Ponto;

public class JanelaPrincipal extends JFrame {

	private ArrayList<String> listaNomePontos = new ArrayList<String>();
	private ArrayList<Circulo> listaVertices = new ArrayList<Circulo>();
	private ArrayList<Linha> listaLinhas = new ArrayList<Linha>();
	private JPanel contentPane;
	private Controller controller = new Controller();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setTitle("Buscador de Rota");
		setResizable(false);
		
		JPanel painelBotoes = new JPanel();
		
		final JPanel painelGrafo = new JPanel();
		painelGrafo.setBackground(SystemColor.desktop);
		painelGrafo.setForeground(Color.BLACK);
		
		JPanel panelImagem = new JPanel();
		
		// Imagem retirada de: http://www.transvipbr.com.br/assets/img/iconCarroForte.png
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("iconCarroForte.png"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		JLabel imagem = new JLabel(new ImageIcon(myPicture));
		
		panelImagem.add(imagem);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(painelBotoes, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(panelImagem, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(painelGrafo, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(painelBotoes, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelImagem, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(painelGrafo, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
					.addGap(21))
		);
		GroupLayout gl_painelGrafo = new GroupLayout(painelGrafo);
		gl_painelGrafo.setHorizontalGroup(
			gl_painelGrafo.createParallelGroup(Alignment.LEADING)
				.addGap(0, 448, Short.MAX_VALUE)
		);
		gl_painelGrafo.setVerticalGroup(
			gl_painelGrafo.createParallelGroup(Alignment.LEADING)
				.addGap(0, 379, Short.MAX_VALUE)
		);
		painelGrafo.setLayout(gl_painelGrafo);
		
		// Botao responsavel por cadastrar um ponto no grafo
		JButton btnCadastrarPonto = new JButton("Cadastrar Ponto");
		btnCadastrarPonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JOptionPane.showMessageDialog(painelGrafo, "Clique na tela para cadastrar um ponto");
				// Indica as acoes a serem executadas caso o usuario clique na tela
				MouseAdapter cliqueCadastro = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						// Pede ao usuario que insira o nome do ponto e salva o retorno 
						String nomeDoLocal = JOptionPane.showInputDialog("Insira o nome do ponto:");
						boolean nomeDisponivel = false;
						
						// As coordenadas do ponto na telas sao salvas
						int x = e.getX();
						int y = e.getY();
						
						// Confere se o nome inserido ja foi cadastrado
						while(!nomeDisponivel){
							try {
								controller.cadastrarPonto(nomeDoLocal, x, y);
								nomeDisponivel = true;
							} catch (PontoJaCadastradoException e1) {
								nomeDoLocal = JOptionPane.showInputDialog("Nome ja cadastrado. Insira outro nome para o ponto:");
							} catch (PontoComNomeNuloException e1) {
								return;
							}
						}
						listaNomePontos.add(nomeDoLocal);
						
						// Adiciona parte grafica do ponto
						Circulo circulo = new Circulo(x,y);
						
						Dimension dimensao = circulo.getPreferredSize();
				
						circulo.setLocation(x, y);
						circulo.setSize(dimensao);
						painelGrafo.add(circulo);
						listaVertices.add(circulo);
					
						painelGrafo.repaint();

						// Remove MouseListener do painel grafo, para tentar adicionar ponto com clique apos ja ter cadastrado um
						painelGrafo.removeMouseListener(painelGrafo.getMouseListeners()[0]);
					}
			};
				painelGrafo.addMouseListener(cliqueCadastro);
			}
	
		});
		
		listaNomePontos.add("");
		
		
		JButton btnCadastrarAresta = new JButton("Cadastrar Aresta");
		btnCadastrarAresta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JPanel painelCadastro = new JPanel();
				
				painelCadastro.setLayout(new BoxLayout(painelCadastro, BoxLayout.PAGE_AXIS));
				
				JLabel primeiroPonto = new JLabel("Selecione o primeiro ponto:");
				JLabel segundoPonto = new JLabel("Selecione o segundo ponto: ");
				JLabel duracao = new JLabel("Insira a duracao do percurso:");
				
				
				JComboBox<Object> comboBox = new JComboBox<Object>(listaNomePontos.toArray());
				JComboBox<Object> comboBox2 = new JComboBox<Object>(listaNomePontos.toArray());
				
				JTextField duracaoTexto = new JTextField(10);
				
				painelCadastro.add(primeiroPonto);
				painelCadastro.add(comboBox);
				painelCadastro.add(segundoPonto);
				painelCadastro.add(comboBox2);
				painelCadastro.add(duracao);
				painelCadastro.add(duracaoTexto);
		
				
				if(JOptionPane.showConfirmDialog(null, painelCadastro, "Cadastro de aresta", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					if(duracaoTexto.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Duracao do percurso nao foi inserida! Tente novamente");
					
					else{	
						String nome1 = comboBox.getSelectedItem().toString();
						String nome2 = comboBox2.getSelectedItem().toString();
						int  duracaoRecebida = Integer.parseInt(duracaoTexto.getText());
						
						Ponto ponto1 = controller.buscarPonto(nome1);
						Ponto ponto2 = controller.buscarPonto(nome2);
						
						
					
						try {
							controller.cadastrarAresta(ponto1, ponto2, duracaoRecebida, ponto1.getNomeDoLocal()+ponto2.getNomeDoLocal());
						} catch (NumberFormatException | PontoNaoExistenteException e1) {
							e1.printStackTrace();
						} catch (ArestaJaCadastradaException e1){
							JPanel painelArestaJaCadastrada = new JPanel();
							JLabel aviso = new JLabel("Trajeto ja cadastrado. Deseja alterar duracao do trajeto para duracao informada?");
							painelArestaJaCadastrada.add(aviso);
							
							// Caso queira alterar a duracao da aresta para duracao informada
							if(JOptionPane.showConfirmDialog(null,  painelArestaJaCadastrada, "Aresta ja cadastrada!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
								
								// Modifica duracao da aresta P1->P2
								Iterator<Aresta> i = ponto1.getListaArestas().iterator();
								Aresta aux;
								while (i.hasNext()){
									aux = i.next();
									if (aux.getPontoSeguinte() == ponto2){
										aux.setDuracao(duracaoRecebida);
										break;
									}
								}
								
								// Modifica duracao da aresta P2->P1
								i = ponto2.getListaArestas().iterator();
								while (i.hasNext()) {
									aux = i.next();
									if (aux.getPontoSeguinte() == ponto1){ 
										aux.setDuracao(duracaoRecebida);
										break;
									}
								}
							} else{ // Caso nao queira, se enganou. Fecha cadastro de aresta.
								return;
							}
						}
						
						// Constroi a linha passando a coordenada dos pontos mais o raio dos mesmos
						Linha aresta = new Linha(ponto1.getCoordX()+10, ponto1.getCoordY()+10, ponto2.getCoordX()+10, ponto2.getCoordY()+10, ponto1.getNomeDoLocal()+ponto2.getNomeDoLocal());
						aresta.setSize(getPreferredSize());
					
						listaLinhas.add(aresta);
						
						painelGrafo.add(aresta);
						painelGrafo.repaint();
					}
				}
			}
		});
		
		JButton btnRemoverPonto = new JButton("Remover Ponto");
		
		btnRemoverPonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
			
				JPanel painelRemocao = new JPanel();
				
				JLabel primeiroPonto = new JLabel("Selecione o ponto a ser removido:");
				
				
				JComboBox<Object> comboBox = new JComboBox<Object>(listaNomePontos.toArray());
								
				painelRemocao.add(primeiroPonto);
				painelRemocao.add(comboBox);
			
				// Caso o usuario selecione o botao OK, armazena o valor dos dados selecionados pelo usuario
				if(JOptionPane.showConfirmDialog(null, painelRemocao, "Remocao de ponto", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					String nome = comboBox.getSelectedItem().toString();
					
					Ponto ponto = controller.buscarPonto(nome);
					
					// Cria uma copia da lista de arestas do ponto, para auxiliar na remocao da aresta grafica
					ArrayList<Aresta> listaParaRemover = (ArrayList<Aresta>)ponto.getListaArestas().clone();
					
					// Remove o ponto
					controller.removerPonto(ponto);
									
					// Itera a lista de nome dos pontos e remove o nome correspondente ao ponto removido
					Iterator<String> itr = listaNomePontos.iterator();
					while(itr.hasNext())
					{
						String nomePonto = itr.next();
						if(nomePonto.equals(nome)){
							listaNomePontos.remove(nomePonto);
							break;
							}
					}
					
					// Itera a lista de vertices do grafo grafico e remove os nos correspondentes 
					Iterator<Circulo> itera = listaVertices.iterator();
					while(itera.hasNext()){
						Circulo circulo = itera.next();
						
						if(circulo.getCoordX() == ponto.getCoordX() && circulo.getCoordY() == ponto.getCoordY())
						{
							listaVertices.remove(circulo);
							painelGrafo.remove(circulo);
							break;
						}
					}

					int i = 0;

					// Itera a lista de arestas a serem removidas e remove as linhas correspondentes no grafo
					for(i = 0; i<listaParaRemover.size(); i++){
						Aresta aresta = listaParaRemover.get(i);
						
						for(int j = 0; j<listaLinhas.size(); j++){
						
						Linha linha = listaLinhas.get(j);
						
						if(aresta.getNome().equals(linha.getNome()))
								{
									listaLinhas.remove(linha);
									painelGrafo.remove(linha);
								}
						}
					}
					
					// Repinta o grafo com o ponto e suas respectivas arestas ja removidos
					painelGrafo.repaint();
				}
			}
			
		});
		
		JButton btnRemoverAresta = new JButton("Remover Aresta");
		btnRemoverAresta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Escolhe aresta a ser removida com base em seus pontos.
				JPanel painelRemoveAresta = new JPanel();
				
				JLabel lblPonto1 = new JLabel("Selecione um ponto da aresta");
				JLabel lblPonto2 = new JLabel("Selecione o outro ponto da aresta");
				
				JComboBox<Object> comboBox = new JComboBox<Object>(listaNomePontos.toArray());
				JComboBox<Object> comboBox2 = new JComboBox<Object>(listaNomePontos.toArray());
				
				painelRemoveAresta.add(lblPonto1);
				painelRemoveAresta.add(comboBox);
				painelRemoveAresta.add(lblPonto2);
				painelRemoveAresta.add(comboBox2);

				String nomePonto1 = null;
				String nomePonto2 = null;
				
				Ponto ponto1 = null;
				Ponto ponto2 = null;
				
				if(JOptionPane.showConfirmDialog(null, painelRemoveAresta, "Remover Aresta", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					nomePonto1 = comboBox.getSelectedItem().toString();
					nomePonto2 = comboBox2.getSelectedItem().toString();
				
					ponto1 = controller.buscarPonto(nomePonto1);
					ponto2 = controller.buscarPonto(nomePonto2);
				}
				
				
				// Remove a aresta do sistema
				controller.removerAresta(ponto1, ponto2);
				
				
				// Remove aresta do grafo
				Linha aux;
				Iterator<Linha> i = listaLinhas.iterator();
				while (i.hasNext()){
					aux = i.next();
					if (aux.getNome().equals(nomePonto1+nomePonto2) || aux.getNome().equals(nomePonto2+nomePonto1)){
						listaLinhas.remove(aux);
						painelGrafo.remove(aux);
						break;
					}
				}
				
				// Repinta grafo
				painelGrafo.repaint();
				
			}
		});
		
		JButton btnCalcularRota = new JButton("Calcular Rota");
		
		btnCalcularRota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg){
				// ArrayList de arestas que recebera o caminho
				ArrayList<Aresta> caminho = new ArrayList<Aresta>();
				
				// Escolhe o ponto inicial, coleta e final.
				JPanel painelRota = new JPanel();
				painelRota.setLayout(new BoxLayout(painelRota, BoxLayout.PAGE_AXIS));
				
				JLabel garagem = new JLabel("Selecione a garagem:");
				JLabel coleta = new JLabel("Selecione o ponto de coleta: ");
				JLabel banco = new JLabel("Selecione o banco de destino final:");
				
				
				JComboBox<Object> comboBox = new JComboBox<Object>(listaNomePontos.toArray());
				JComboBox<Object> comboBox2 = new JComboBox<Object>(listaNomePontos.toArray());
				JComboBox<Object> comboBox3 = new JComboBox<Object>(listaNomePontos.toArray());
				
				painelRota.add(garagem);
				painelRota.add(comboBox);
				painelRota.add(coleta);
				painelRota.add(comboBox2);
				painelRota.add(banco);
				painelRota.add(comboBox3);
		
				Ponto pontoInicial = null;
				Ponto pontoColeta = null;
				Ponto pontoFinal = null;
				
				if(JOptionPane.showConfirmDialog(null, painelRota, "Calcular Rota", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					String nomeGaragem = comboBox.getSelectedItem().toString();
					String nomeColeta = comboBox2.getSelectedItem().toString();
					String nomeBanco = comboBox3.getSelectedItem().toString();
				
					pontoInicial = controller.buscarPonto(nomeGaragem);
					pontoColeta = controller.buscarPonto(nomeColeta);
					pontoFinal = controller.buscarPonto(nomeBanco);
				}
				
				// Ponto inicial pode ser definido previamente, como definir garagem, ou combobox
				// Ponto final pode ser definido previamente, como definir banco?, ou combobox
				// Escolher ponto coleta com combobox, sem os pontos inicial e final, claro.
								
				
				// Calcula rota
				try {
					int distancia = controller.calcularRota(caminho, pontoInicial, pontoColeta, pontoFinal);
				} catch (PontoNaoExistenteException e) { // Caso ponto nao exista
					e.printStackTrace();
				}
				
				
				// Muda a cor das arestas do caminho no grafo
				Linha linhaAux;
				Iterator<Aresta> i = caminho.iterator();
				Aresta aux;
				while (i.hasNext()){
					aux = i.next();
					Iterator<Linha> itrLinha = listaLinhas.iterator();
					while(itrLinha.hasNext()){
						linhaAux = itrLinha.next();
						if (aux.getNome().equals(linhaAux.getNome()) ){
							// Muda a cor da aresta no grafo
							linhaAux.paintComponentRed(painelGrafo.getGraphics());
							break;
						}
					}
				}
			}
		});
		GroupLayout gl_painelBotoes = new GroupLayout(painelBotoes);
		gl_painelBotoes.setHorizontalGroup(
			gl_painelBotoes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelBotoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelBotoes.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCadastrarPonto, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addComponent(btnCadastrarAresta, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addComponent(btnRemoverPonto, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addComponent(btnRemoverAresta, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addComponent(btnCalcularRota, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
					.addGap(21))
		);
		gl_painelBotoes.setVerticalGroup(
			gl_painelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelBotoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCadastrarPonto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCadastrarAresta)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemoverPonto)
					.addGap(3)
					.addComponent(btnRemoverAresta)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCalcularRota)
					.addGap(222))
		);
		painelBotoes.setLayout(gl_painelBotoes);
		
		
		contentPane.setLayout(gl_contentPane);
	}
}
