package uade.ioo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import uade.ioo.controller.Controlador;
import uade.ioo.exception.ValidationException;

public class NuevoUsuarioFrame extends BaseViewFrame {

	private static final long serialVersionUID = 8664114819649806499L;
	private final static String USER_NAME = "Nombre de usuario:";
	private final static String FIRST_NAME = "Nombre:";
	private final static String LAST_NAME = "Apellido:";
	private final static String BACK = "Volver";
	private final static String ACEPTAR = "Aceptar";
	private JTextField usuarioField;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JLabel usuarioVacio;
	private JLabel nombreVacio;
	private JLabel apellidoVacio;
	private JLabel usuarioYaExiste;

	public NuevoUsuarioFrame(Controlador controller) {
		super(controller);
	}

	protected void init() {
		JLabel usuarioLabel = new JLabel(USER_NAME);
		usuarioLabel.setBounds(20, 20, 250, 20);
		this.usuarioField = new JTextField();
		this.usuarioField.setBounds(270, 20, 200, 20);

		JLabel firstNameLabel = new JLabel(FIRST_NAME);
		firstNameLabel.setBounds(20, 40, 250, 20);
		this.nombreField = new JTextField();
		nombreField.setBounds(270, 40, 200, 20);

		JLabel lastNameLabel = new JLabel(LAST_NAME);
		lastNameLabel.setBounds(20, 60, 250, 20);
		this.apellidoField = new JTextField();
		apellidoField.setBounds(270, 60, 200, 20);

		JButton volverButton = new JButton(BACK);
		volverButton.setBounds(160, 400, 100, 30);
		volverButton.addActionListener(new VolverActionListener());

		JButton aceptarButton = new JButton(ACEPTAR);
		aceptarButton.setBounds(260, 400, 130, 30);
		aceptarButton.addActionListener(new AceptarActionListener(this));

		usuarioVacio = new JLabel("El nombre de usuario es requerido");
		usuarioVacio.setBounds(20, 110, 300, 20);

		nombreVacio = new JLabel("El nombre es requerido");
		nombreVacio.setBounds(20, 130, 200, 20);

		apellidoVacio = new JLabel("El apellido es requerido");
		apellidoVacio.setBounds(20, 150, 200, 20);

		usuarioYaExiste = new JLabel();
		usuarioYaExiste.setBounds(20, 170, 200, 20);

		add(usuarioLabel);
		add(firstNameLabel);
		add(lastNameLabel);
		add(usuarioField);
		add(nombreField);
		add(apellidoField);
		add(volverButton);
		add(aceptarButton);
	}

	private void volver() {
		LoginFrame loginFrame = new LoginFrame(controller);
		this.setVisible(false);
		loginFrame.setVisible(true);
	}

	public class AceptarActionListener implements ActionListener {
		private NuevoUsuarioFrame nuevoUsuarioFrame;

		public AceptarActionListener(NuevoUsuarioFrame nuevoUsuarioFrame) {
			this.nuevoUsuarioFrame = nuevoUsuarioFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean hasError = false;
			nuevoUsuarioFrame.remove(usuarioYaExiste);
			nuevoUsuarioFrame.remove(usuarioVacio);
			nuevoUsuarioFrame.remove(nombreVacio);
			nuevoUsuarioFrame.remove(apellidoVacio);

			if (nuevoUsuarioFrame.usuarioField.getText() == null
					|| nuevoUsuarioFrame.usuarioField.getText().isEmpty()) {
				nuevoUsuarioFrame.add(usuarioVacio);
				hasError = true;
			}

			if (nuevoUsuarioFrame.nombreField.getText() == null || nuevoUsuarioFrame.nombreField.getText().isEmpty()) {
				hasError = true;
				nuevoUsuarioFrame.add(nombreVacio);
			}

			if (nuevoUsuarioFrame.apellidoField.getText() == null
					|| nuevoUsuarioFrame.apellidoField.getText().isEmpty()) {
				hasError = true;
				nuevoUsuarioFrame.add(apellidoVacio);
			}

			if (!hasError) {
				try {
					controller.crearUsuario(nuevoUsuarioFrame.usuarioField.getText(),
							nuevoUsuarioFrame.nombreField.getText(), nuevoUsuarioFrame.apellidoField.getText());
					volver();
				} catch (ValidationException ve) {
					usuarioYaExiste.setText(ve.getMessage());
					nuevoUsuarioFrame.add(usuarioYaExiste);
				}
			}
		}
	}

	public class VolverActionListener implements ActionListener {

		public VolverActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			volver();
		}
	}
}
