package main.view.pannels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import main.controller.LoginController;
import main.manager.StatusSingleton;
import main.backups.BackupMaker;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoginPannel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField userTextField;
	private JTextField passwordField;
	private JLabel logoImage = null;
	private JLabel logInTitle = null;
	private JLabel appTitleLbl = null;
	private JLabel userLbl = null;
	private JLabel passwordLbl = null;
	private JButton continueButton = null;
	private JButton registerButton = null;
	private JLabel noAccountLbl = null;

	private BackupMaker backupMaker = null;

	public LoginPannel() {

		setLayout(null);
		setBackground(new Color(48, 48, 48));
		setBounds(0, 0, 1230, 700);

		logoImage = new JLabel();
		logoImage.setIcon(new ImageIcon("src/main/resources/logo.png"));
		logoImage.setBounds(38, 27, 112, 112);
		add(logoImage);

		logInTitle = new JLabel("Login");
		logInTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		logInTitle.setForeground(new Color(255, 193, 7));
		logInTitle.setHorizontalAlignment(SwingConstants.CENTER);
		logInTitle.setBounds(417, 189, 219, 39);
		add(logInTitle);

		appTitleLbl = new JLabel("ERREKAFIT");
		appTitleLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 64));
		appTitleLbl.setForeground(new Color(255, 193, 7));
		appTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		appTitleLbl.setBounds(344, 27, 487, 56);
		add(appTitleLbl);

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
		userLbl.setForeground(Color.white);
		add(userLbl);

		passwordLbl = new JLabel("Contraseña");
		passwordLbl.setBounds(493, 395, 81, 14);
		passwordLbl.setForeground(Color.white);
		add(passwordLbl);

		continueButton = new JButton("Continuar");
		continueButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (userIsCorrect()) {
						JOptionPane.showMessageDialog(null, "Bienvenido, cliente!", "Login OK!",
								JOptionPane.INFORMATION_MESSAGE);
						backupMaker = new BackupMaker();
						backupMaker.doBackup();
						StatusSingleton.getInstance().changeToWorkoutsPannel();
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "No hay internet, continuando con los backups...", "Error!",
							JOptionPane.ERROR_MESSAGE);
					try {
						backupMaker = new BackupMaker();
						backupMaker.getBackup();
						StatusSingleton.getInstance().offline = true;
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Ha habido un error escribiendo/leyendo los backups...",
								"Error!", JOptionPane.ERROR_MESSAGE);
					} catch (InterruptedException e1) {
						JOptionPane.showMessageDialog(null, "Ha habido un error en el proceso de los backups...",
								"Error!", JOptionPane.ERROR_MESSAGE);
					}

					StatusSingleton.getInstance().changeToWorkoutsPannel();
				}
			}
		});
		continueButton.setBounds(1065, 621, 140, 34);
		add(continueButton);

		registerButton = new JButton("Registrate");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StatusSingleton.getInstance().changeToRegisterPannel();
			}
		});
		registerButton.setBounds(10, 621, 140, 34);
		add(registerButton);

		noAccountLbl = new JLabel("¿No tienes cuenta?");
		noAccountLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noAccountLbl.setForeground(Color.white);
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

	public JPanel getLoginPanel() {
		return this;
	}
}
