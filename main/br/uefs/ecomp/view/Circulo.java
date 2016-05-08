package br.uefs.ecomp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Circulo extends JPanel {

	Ellipse2D.Double circulo;
	
	public Circulo()
	{
		circulo = new Ellipse2D.Double(0,0, 15, 15);
		setOpaque(false);
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
	
}
