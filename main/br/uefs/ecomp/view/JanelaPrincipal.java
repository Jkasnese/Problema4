package br.uefs.ecomp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.uefs.ecomp.controller.Controller;
import br.uefs.ecomp.exceptions.PontoComNomeNuloException;
import br.uefs.ecomp.exceptions.PontoJaCadastradoException;
import br.uefs.ecomp.model.Ponto;

public class JanelaPrincipal extends JFrame {

	ArrayList<String> listaNomePontos = new ArrayList<String>();
	ArrayList<Circulo> listaVertices = new ArrayList<Circulo>();
	
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setTitle("Buscador de Rota");
		
		JPanel painelBotoes = new JPanel();
		
		final JPanel painelGrafo = new JPanel();
		painelGrafo.setBackground(SystemColor.desktop);
		painelGrafo.setForeground(Color.BLACK);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(painelBotoes, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(painelGrafo, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(painelGrafo, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
						.addComponent(painelBotoes, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JButton btnCadastrarPonto = new JButton("Cadastrar Ponto");
		
		btnCadastrarPonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JOptionPane.showMessageDialog(painelGrafo, "Clique na tela para cadastrar um ponto");
				
				MouseAdapter cliqueCadastro = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						String nomeDoLocal = JOptionPane.showInputDialog("Insira o nome do ponto:");
						boolean nomeDisponivel = false;
						
						while(!nomeDisponivel){
							try {
								controller.cadastrarPonto(nomeDoLocal, e.getX(), e.getY());
								nomeDisponivel = true;
							} catch (PontoJaCadastradoException e1) {
								nomeDoLocal = JOptionPane.showInputDialog("Nome ja cadastrado. Insira outro nome para o ponto:");
							} catch (PontoComNomeNuloException e1) {
								return;
							}
						}
						listaNomePontos.add(nomeDoLocal);
						// Adiciona parte grafica do ponto
						Circulo circulo = new Circulo();
						circulo.setLocation(e.getX(), e.getY());
						circulo.setSize(circulo.getPreferredSize());
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
		painelBotoes.add(btnCadastrarPonto);
		
		
		
		JButton btnCadastrarAresta = new JButton("Cadastrar Aresta");
		btnCadastrarAresta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
//				Object[] vetor = listaNomePontos.toArray();
//				final JanelaCadastroAresta janela = new JanelaCadastroAresta(vetor);
//				
				JPanel painelCadastro = new JPanel();
				JComboBox<Object> comboBox = new JComboBox<Object>(listaNomePontos.toArray());
				JLabel primeiroPonto = new JLabel("Selecione o primeiro ponto:");
				JLabel segundoPonto = new JLabel("Selecione o segundo ponto: ");
				JComboBox<Object> comboBox2 = new JComboBox<Object>(listaNomePontos.toArray());
				painelCadastro.add(primeiroPonto);
				painelCadastro.add(comboBox);
				painelCadastro.add(segundoPonto);
				painelCadastro.add(comboBox2);
				
				if(JOptionPane.showConfirmDialog(null, painelCadastro, "Cadastro de aresta", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
				
				String nome1 = comboBox.getSelectedItem().toString();
				String nome2 = comboBox2.getSelectedItem().toString();
				
				System.out.println(nome1);
				System.out.println(nome2);
				
				Ponto ponto1 = controller.buscarPonto(nome1);
				Ponto ponto2 = controller.buscarPonto(nome2);
				
				
				Linha aresta = new Linha(ponto1.getCoordX(), ponto1.getCoordY(), ponto2.getCoordX(), ponto2.getCoordY());
				painelGrafo.add(aresta);
				painelGrafo.repaint();
			}
			}
		});
		painelBotoes.add(btnCadastrarAresta);
		
		JButton btnRemoverPonto = new JButton("Remover Ponto");
		
		btnRemoverPonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg) {
			}
			
		});
		
		painelBotoes.add(btnRemoverPonto);
		
		JButton btnRemoverAresta = new JButton("Remover Aresta");
		painelBotoes.add(btnRemoverAresta);
		
		JButton btnCalcularRota = new JButton("Calcular Rota");
		painelBotoes.add(btnCalcularRota);
		contentPane.setLayout(gl_contentPane);
	}
}
