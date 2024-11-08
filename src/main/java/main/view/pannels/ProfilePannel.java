package main.view.pannels;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.Color;
import javax.swing.SwingConstants;

import main.controller.UserController;
import main.manager.StatusSingleton;
import main.manager.pojo.User;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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
	private JLabel logoImage = null;

	private JButton backBtn = null;
	private JButton btnCambiarContrasea = null;

	private String newInfoToInsert = null;
	private String newInfoConfirm = null;

	public ProfilePannel() {

		setBounds(0, 0, 1230, 700);
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

		logoImage = new JLabel();
		logoImage.setIcon(new ImageIcon("src/main/resources/logo.png"));
		logoImage.setBounds(38, 27, 112, 112);
		add(logoImage);

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
		lblRegisterName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeUserData("name");
			}
		});
		add(lblRegisterName);

		lblRegisterFirstSurname = new JLabel("Primer apellido: ");
		lblRegisterFirstSurname.setForeground(Color.WHITE);
		lblRegisterFirstSurname.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterFirstSurname.setBounds(90, 350, 365, 32);
		lblRegisterFirstSurname.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeUserData("surname");
			}
		});
		add(lblRegisterFirstSurname);

		lblRegisterMail = new JLabel("Correo electronico: ");
		lblRegisterMail.setForeground(Color.WHITE);
		lblRegisterMail.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterMail.setBounds(90, 400, 465, 43);
		lblRegisterMail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeUserData("mail");
			}
		});
		add(lblRegisterMail);

		lblRegisterBirthDate = new JLabel("Fecha de Nacimiento: ");
		lblRegisterBirthDate.setForeground(Color.WHITE);
		lblRegisterBirthDate.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterBirthDate.setBounds(90, 458, 555, 32);
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
		lblRegisterPasswd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeUserData("pass");
			}
		});
		add(lblRegisterPasswd);

		lblRegisterUserType = new JLabel("Tipo de Usuario: ");
		lblRegisterUserType.setForeground(Color.WHITE);
		lblRegisterUserType.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		lblRegisterUserType.setBounds(679, 362, 324, 31);
		add(lblRegisterUserType);

		btnCambiarContrasea = new JButton("Cambiar fecha");
		btnCambiarContrasea.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				changeDate();
			}
		});
		btnCambiarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCambiarContrasea.setBounds(80, 599, 192, 33);
		add(btnCambiarContrasea);

	}

	public void showUserInfor() {
		userProfile = new User();
		userProfile = StatusSingleton.getInstance().getUser();
		if (null != userProfile) {
			lblRegisterName.setText("Nombre usuario: " + userProfile.getName());
			lblRegisterPasswd.setText("Contrasena: " + userProfile.getPass());
			lblRegisterMail.setText("Correo electronico: " + userProfile.getMail());
			lblRegisterFirstSurname.setText("Apellido: " + userProfile.getSurname());
			lblRegisterBirthDate.setText("Fecha de nacimiento: " + userProfile.getBirthDate().toString());
			if (userProfile.isTrainer()) {
				lblRegisterUserType.setText("Tipo de usuario: Entrenador");
			} else if (!userProfile.isTrainer())
				lblRegisterUserType.setText("Tipo de usuario: Cliente");
		} else
			JOptionPane.showMessageDialog(null, "No se ha podido cargar la información.", "Error",
					JOptionPane.ERROR_MESSAGE);

	}

	private void changeUserData(String option) {
		newInfoToInsert = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario: ");
		newInfoConfirm = JOptionPane.showInputDialog("Por favor, repita el nuevo nombre de usuario: ");
		try {
			if (newInfoToInsert.equals(newInfoConfirm)) {

				switch (option) {
				case ("name"):
					userProfile.setName(newInfoConfirm);
					break;
				case ("pass"):
					userProfile.setPass(newInfoConfirm);
					break;
				case ("mail"):
					userProfile.setMail(newInfoConfirm);
					break;
				case ("surname"):
					userProfile.setSurname(newInfoConfirm);

				}

				if (userController.changeUser(userProfile)) {
					JOptionPane.showMessageDialog(null, "Nombre de usuario cambiado correctamente", "OK!!",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} else
				JOptionPane.showMessageDialog(null, "No se ha introducido la misma contraseña en los dos campos",
						"Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido cambiar la contraseña", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void changeDate() {

		UtilDateModel model = new UtilDateModel();

		JDatePickerImpl datePicker = new JDatePickerImpl(new JDatePanelImpl(model));

		int result = JOptionPane.showConfirmDialog(null, datePicker, "Selecciona una fecha",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			Date selectedDate = (Date) datePicker.getModel().getValue();

			if (selectedDate != null) {
				userProfile.setBirthDate(selectedDate);

				try {
					if (userController.changeUser(userProfile)) {
						JOptionPane.showMessageDialog(null, "Fecha cambiada correctamente", "OK!!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se ha podido cambiar la fecha", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
