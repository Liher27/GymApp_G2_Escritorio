package main.view.pannels;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import main.controller.LoginController;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPannel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField userTextField;
	private JTextField passwordField;
	private JLabel logoImage = null;
	private JLabel logInTitle = null;
	private JLabel appTitle = null;
	private JLabel userLbl = null;
	private JLabel passwordLbl = null;
	private JButton continueButton = null;
	private JButton registerButton = null;
	private JLabel noAccountLbl = null;

	public LoginPannel(List<JPanel> pannels) {
		setLayout(null);
		setBounds(0, 0, 1230, 700);

		logoImage = new JLabel("Logo aqui");
		logoImage.setBounds(38, 27, 87, 87);
		add(logoImage);

		logInTitle = new JLabel("Log In");
		logInTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		logInTitle.setHorizontalAlignment(SwingConstants.CENTER);
		logInTitle.setBounds(417, 189, 219, 39);
		add(logInTitle);

		appTitle = new JLabel("ERREKAFIT");
		appTitle.setFont(new Font("Segoe UI Semibold", Font.BOLD, 64));
		appTitle.setHorizontalAlignment(SwingConstants.CENTER);
		appTitle.setBounds(344, 27, 487, 56);
		add(appTitle);

		userTextField = new JTextField();
		userTextField.setBounds(493, 289, 219, 20);
		add(userTextField);
		userTextField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(493, 420, 219, 20);
		add(passwordField);

		userLbl = new JLabel("Usuario");
		userLbl.setBounds(493, 264, 81, 14);
		add(userLbl);

		passwordLbl = new JLabel("Contraseña");
		passwordLbl.setBounds(493, 395, 81, 14);
		add(passwordLbl);

		continueButton = new JButton("Continuar");
		continueButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (userIsCorrect()) {
						JOptionPane.showMessageDialog(null, "Bienvenido, cliente!", "Log in OK!",
								JOptionPane.INFORMATION_MESSAGE);
						pannels.get(0).setVisible(false);
						pannels.get(1).setVisible(false);
						pannels.get(2).setVisible(true);
						pannels.get(3).setVisible(false);
						pannels.get(4).setVisible(false);
						pannels.get(5).setVisible(false);
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Ha habido un error en la base de datos", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		continueButton.setBounds(1065, 621, 140, 34);
		add(continueButton);

		registerButton = new JButton("Registrate");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pannels.get(0).setVisible(false);
				pannels.get(1).setVisible(true);
				pannels.get(2).setVisible(false);
				pannels.get(3).setVisible(false);
				pannels.get(4).setVisible(false);
				pannels.get(5).setVisible(false);
			}
		});
		registerButton.setBounds(10, 621, 140, 34);
		add(registerButton);

		noAccountLbl = new JLabel("¿No tienes cuenta?");
		noAccountLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noAccountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		noAccountLbl.setBounds(10, 596, 140, 14);
		add(noAccountLbl);

	}

	private boolean userIsCorrect() throws Exception {
		if (!userTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
			if (new LoginController().loginUser(userTextField.getText(), passwordField.getText())) {
				return true;
			} else
				JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario...", "Error!",
						JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Los campos estan vacios", "Error!", JOptionPane.ERROR_MESSAGE);
		return false;
	}

}
