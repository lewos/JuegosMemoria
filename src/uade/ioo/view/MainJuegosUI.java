package uade.ioo.view;

import uade.ioo.controller.Controlador;

public class MainJuegosUI {

	private Controlador controller;

	public MainJuegosUI() {
		this.controller = new Controlador();
		this.initUIs();
	}

	private void initUIs() {
		LoginFrame login = new LoginFrame(this.controller);
		login.setVisible(true);
	}

	public static void main(String[] args) {
		new MainJuegosUI();
	}
}
