package br.uefs.ecomp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Linha extends JPanel {

	
	int x1, x2, y1, y2;
	
	public Linha(int x1, int y1, int x2, int y2){
	
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		setOpaque(false);
	}

	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.draw(new Line2D.Double(x1, y1, x2, y2));
		
	}
	
}
