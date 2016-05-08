package br.uefs.ecomp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.uefs.ecomp.controller.Controller;

public class JanelaPrincipal extends JFrame {

	private JPanel contentPane;

	Controller controller = new Controller();
	
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
		setBounds(100, 100, 506, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
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
					.addComponent(painelGrafo, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(painelGrafo, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
						.addComponent(painelBotoes, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton btnCadastrarPonto = new JButton("Cadastrar Ponto");
		
		btnCadastrarPonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				MouseAdapter cliqueCadastro = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						String nomeDoLocal = JOptionPane.showInputDialog("Insira o nome do ponto:");
						
						controller.cadastrarPonto(nomeDoLocal, e.getX(), e.getY());
					}
			};
				painelGrafo.addMouseListener(cliqueCadastro);
			}
	
		});
	
		
		painelBotoes.add(btnCadastrarPonto);
		
		JButton btnCadastrarAresta = new JButton("Cadastrar Aresta");
		painelBotoes.add(btnCadastrarAresta);
		
		JButton btnRemoverPonto = new JButton("Remover Ponto");
		painelBotoes.add(btnRemoverPonto);
		
		JButton btnRemoverAresta = new JButton("Remover Aresta");
		painelBotoes.add(btnRemoverAresta);
		
		JButton btnCalcularRota = new JButton("Calcular Rota");
		painelBotoes.add(btnCalcularRota);
		contentPane.setLayout(gl_contentPane);
	}
}
