package uade.ioo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import uade.ioo.controller.Controlador;
import uade.ioo.model.Usuario;

public class PreferenciasFrame extends BaseViewFrame {

	private static final long serialVersionUID = 6641242073318918562L;
	private final static String USER_NAME = "Nombre de usuario:";
	private final static String FIRST_NAME = "Nombre:";
	private final static String LAST_NAME = "Apellido:";
	private final static String BACK = "Volver";
	private final static String ACEPTAR = "Aceptar";
	private final static String ELIMINAR = "Eliminar Usuario";
	private JTextField usuarioField;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JLabel nombreVacio;
	private JLabel apellidoVacio;
	

	public PreferenciasFrame(Controlador controller) {
		super(controller);
	}

	@Override
	protected void init() {
		Usuario usuarioActual = this.controller.getUsuarioActual();
		JLabel usuarioLabel = new JLabel(USER_NAME);
		usuarioLabel.setBounds(20, 20, 250, 20);
		this.usuarioField = new JTextField();
		this.usuarioField.setBounds(270, 20, 200, 20);
		this.usuarioField.setText(usuarioActual.getId());
		this.usuarioField.setEnabled(false);

		JLabel firstNameLabel = new JLabel(FIRST_NAME);
		firstNameLabel.setBounds(20, 40, 250, 20);
		this.nombreField = new JTextField();
		nombreField.setBounds(270, 40, 200, 20);
		this.nombreField.setText(usuarioActual.getNombre());

		JLabel lastNameLabel = new JLabel(LAST_NAME);
		lastNameLabel.setBounds(20, 60, 250, 20);
		this.apellidoField = new JTextField();
		apellidoField.setBounds(270, 60, 200, 20);
		this.apellidoField.setText(usuarioActual.getApellido());

		JButton volverButton = new JButton(BACK);
		volverButton.setBounds(160, 370, 100, 30);
		volverButton.addActionListener(new VolverActionListener());

		JButton aceptarButton = new JButton(ACEPTAR);
		aceptarButton.setBounds(260, 370, 130, 30);
		aceptarButton.addActionListener(new AceptarActionListener(this));
		
		JButton eliminarButton = new JButton(ELIMINAR);
		eliminarButton.setBounds(210, 400, 130, 30);
		eliminarButton.addActionListener(new EliminarActionListener(this));

		add(usuarioLabel);
		add(firstNameLabel);
		add(lastNameLabel);
		add(usuarioField);
		add(nombreField);
		add(apellidoField);
		add(volverButton);
		add(aceptarButton);
		add(eliminarButton);
		
		nombreVacio = new JLabel("El nombre no debe estar vacio");
		nombreVacio.setBounds(20, 110, 200, 20);
		
		apellidoVacio = new JLabel("El apellido no debe estar vacio");
		apellidoVacio.setBounds(20, 130, 200, 20);
	}

	public class AceptarActionListener implements ActionListener {
		private PreferenciasFrame preferenciasFrame;

		public AceptarActionListener(PreferenciasFrame preferenciasFrame) {
			this.preferenciasFrame = preferenciasFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (preferenciasFrame.nombreField.getText().isEmpty()) {
				preferenciasFrame.add(nombreVacio);
			} else {
				preferenciasFrame.remove(nombreVacio);
			}
			if (preferenciasFrame.apellidoField.getText().isEmpty()) {
				preferenciasFrame.add(apellidoVacio);
			} else {
				preferenciasFrame.remove(apellidoVacio);
			}
			if (!preferenciasFrame.nombreField.getText().isEmpty()
					&& !preferenciasFrame.apellidoField.getText().isEmpty()) {
				controller.modificarUsuario(preferenciasFrame.nombreField.getText(),
						preferenciasFrame.apellidoField.getText());
				volver();
			}

		}
	}
	
	public class EliminarActionListener implements ActionListener {
		private PreferenciasFrame preferenciasFrame;

		public EliminarActionListener(PreferenciasFrame preferenciasFrame) {
			this.preferenciasFrame = preferenciasFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			preferenciasFrame.controller.eliminarUsuario();
			salir();
		}
	}

	public class VolverActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			volver();
		}
	}

	private void volver() {
		MenuPrincipalFrame menu = new MenuPrincipalFrame(controller);
		this.setVisible(false);
		menu.setVisible(true);
	}
	
	private void salir() {
		LoginFrame login = new LoginFrame(controller);
		this.setVisible(false);
		login.setVisible(true);
	}
}
