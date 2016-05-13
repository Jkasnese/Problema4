package br.uefs.ecomp.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Linha extends JPanel {

	private int x1, x2, y1, y2;
	private String nome;
	
	public Linha(int x1, int y1, int x2, int y2, String nome){
	
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.nome = nome;
		setOpaque(false);
	}
	
	public Dimension getPreferredSize(){
		Rectangle bounds = this.getBounds();
		return new Dimension(bounds.width, bounds.height);
	}

	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(3.0f));
		g2.draw(new Line2D.Double(x1, y1, x2, y2));
		
	}
	
	public void paintComponentRed(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(3.0f));
		g2.draw(new Line2D.Double(x1, y1, x2, y2));
		
	}


	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public String getNome() {
		return nome;
	}

	
	
}
