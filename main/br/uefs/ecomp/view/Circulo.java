package br.uefs.ecomp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Circulo extends JPanel {

	Ellipse2D.Double circulo;
	private int coordX, coordY;
	
	public Circulo(int x, int y)
	{
		circulo = new Ellipse2D.Double(0,0, 20, 20);
		setOpaque(false);
		this.coordX = x;
		this.coordY = y;
	}
	
	public Dimension getPreferredSize(){
		Rectangle bounds = circulo.getBounds();
		return new Dimension(bounds.width, bounds.height);
	}
	
	 public void paintComponent(Graphics g)
     {
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D) g;
         g2.setColor(Color.BLACK);
         g2.fill(circulo);

     }

	public Ellipse2D.Double getCirculo() {
		return circulo;
	}

	public void setCirculo(Ellipse2D.Double circulo) {
		this.circulo = circulo;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coorX) {
		this.coordX = coorX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	
}
