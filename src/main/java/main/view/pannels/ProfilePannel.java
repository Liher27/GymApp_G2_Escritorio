package main.view.pannels;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

import main.controller.UserController;
import main.manager.StatusSingleton;
import main.manager.pojo.User;

public class ProfilePannel extends JPanel {

	private static final long serialVersionUID = 1L;

	private User userProfile = null;
	private UserController userController = new UserController();

	private JLabel lblLogoErrekamusic = null;
	private JLabel lblProfileTitle = null;
	private JLabel lblRegisterMail;
	private JLabel lblRegisterName = null;
	private JLabel lblRegisterFirstSurname = null;
	private JLabel lblRegisterPasswd = null;
	private JLabel lblRegisterBirthDate = null;
	private JLabel lblRegisterUserType = null;
	private JLabel lblPersonalOpt = null;
	private JLabel lblCountOpt = null;

	private JButton backBtn = null;
	private JButton btnCambiarContrasea = null;

	private String newPasswordToInsert = null;
	private String newPasswordConfirm = null;

	/**
	 * Create the panel.
	 */
	public ProfilePannel() {

		setBounds(0, 0, 1215, 666);
		setBackground(new Color(0, 0, 0));
		setLayout(null);

		addComponentListener(new ComponentAdapter() {

			public void componentShown(ComponentEvent c) {
				try {
					StatusSingleton.getInstance().getUser().toString();
					showUserInfor();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error en el programa", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		lblProfileTitle = new JLabel("DATOS DEL PERFIL");
		lblProfileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfileTitle.setForeground(new Color(200, 40, 255));
		lblProfileTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 37));
		lblProfileTitle.setBounds(328, 32, 490, 64);
		add(lblProfileTitle);

		backBtn = new JButton("Atrás");
		backBtn.setBounds(1069, 622, 98, 33);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSingleton.getInstance().changeToLoginPannel();
			}
		});

		lblLogoErrekamusic = new JLabel("");
		lblLogoErrekamusic.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoErrekamusic.setBounds(29, -20, 145, 119);
		add(lblLogoErrekamusic);

		lblPersonalOpt = new JLabel("DATOS PERSONALES: ");
		lblPersonalOpt.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonalOpt.setForeground(new Color(190, 30, 255));
		lblPersonalOpt.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblPersonalOpt.setBounds(80, 218, 365, 64);
		add(lblPersonalOpt);

		lblRegisterName = new JLabel("Nombre: ");
		lblRegisterName.setForeground(Color.WHITE);
		lblRegisterName.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterName.setBounds(90, 303, 365, 27);
		add(lblRegisterName);

		lblRegisterFirstSurname = new JLabel("Primer apellido: ");
		lblRegisterFirstSurname.setForeground(Color.WHITE);
		lblRegisterFirstSurname.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterFirstSurname.setBounds(90, 350, 365, 32);
		add(lblRegisterFirstSurname);

		lblRegisterMail = new JLabel("Correo electronico: ");
		lblRegisterMail.setForeground(Color.WHITE);
		lblRegisterMail.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterMail.setBounds(90, 400, 355, 43);
		add(lblRegisterMail);

		lblRegisterBirthDate = new JLabel("Fecha de Nacimiento: ");
		lblRegisterBirthDate.setForeground(Color.WHITE);
		lblRegisterBirthDate.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterBirthDate.setBounds(90, 458, 355, 32);
		add(lblRegisterBirthDate);

		lblCountOpt = new JLabel("DATOS DE LA CUENTA: ");
		lblCountOpt.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountOpt.setForeground(new Color(190, 30, 255));
		lblCountOpt.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblCountOpt.setBounds(669, 218, 365, 64);
		add(lblCountOpt);

		lblRegisterPasswd = new JLabel("Contraseña: ");
		lblRegisterPasswd.setForeground(Color.WHITE);
		lblRegisterPasswd.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterPasswd.setBounds(679, 301, 324, 31);
		add(lblRegisterPasswd);

		lblRegisterUserType = new JLabel("Tipo de Usuario: ");
		lblRegisterUserType.setForeground(Color.WHITE);
		lblRegisterUserType.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterUserType.setBounds(679, 362, 324, 31);
		add(lblRegisterUserType);

		btnCambiarContrasea = new JButton("Cambiar contraseña");
		btnCambiarContrasea.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				newPasswordToInsert = JOptionPane.showInputDialog("Ingrese la nueva contraseña: ");
				newPasswordConfirm = JOptionPane.showInputDialog("Por favor, repita la contraseña: ");

				if (newPasswordToInsert.equals(newPasswordConfirm)) {
					userProfile.setPass(newPasswordConfirm);
					try {
						if (userController.changeUserPassword(userProfile)) {
							JOptionPane.showMessageDialog(null, "Contrasenya cambiada correctamente", "OK!!",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "No se ha podido cambiar la contraseña", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(null, "No se ha introducido la misma contraseña en los dos campos",
							"Error", JOptionPane.ERROR_MESSAGE);
			}

		});
		btnCambiarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCambiarContrasea.setBounds(80, 599, 192, 33);
		add(btnCambiarContrasea);

	}

	public void showUserInfor() {
		userProfile = new User();
		lblRegisterName.setText("Nombre usuario: " + userProfile.getName());
		lblRegisterPasswd.setText("Contrasena: " + userProfile.getPass());
		lblRegisterMail.setText("Correo electronico: " + userProfile.getMail());
		lblRegisterFirstSurname.setText("Apellido: " + userProfile.getSurname());
		lblRegisterBirthDate.setText("Fecha de nacimiento: " + userProfile.getBirthDate().toString());

	}

	public JPanel getProfilePanel() {
		return this;
	}
}
