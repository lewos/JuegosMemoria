package uade.ioo.view;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import uade.ioo.controller.Controlador;

public abstract class BaseViewFrame extends JFrame {

	private static final long serialVersionUID = 2492041154394947374L;
	protected Controlador controller;

	public BaseViewFrame(Controlador controller) {
		this.controller = controller;
		this.setBounds(100, 100, 500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Juegos TPO");
		this.setLayout(null);
		
		this.init();
	}

	@Override
	public Component add(Component comp) {
		Component c = super.add(comp);
		this.validate();
		this.repaint();
		return c;
	}
	
	protected abstract void init();
}
