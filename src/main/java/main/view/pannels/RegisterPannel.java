package main.view.pannels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.controller.RegisterController;
import main.manager.pojo.User;
import main.manager.pojo.Workout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class RegisterPannel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblRegisterEmail = null;
	private JLabel lblRegisterName = null;
	private JLabel lblRegisterSurname = null;
	private JLabel lblRegisterPasswd = null;
	private JLabel lblConfirmPasswd = null;
	private JLabel lblRegisterBirthDate = null;
	private JLabel lblRegisterUserType = null;
	private JLabel registerTitleLbl = null;
	private JLabel personalInfoTitle = null;
	private JLabel lblCountOpt = null;
	private JLabel lblNewLabel = null;

	private JDatePickerImpl datePicker = null;
	private Date selectedDate = null;

	private JTextField passwordField = null;
	private JTextField emailTextField = null;
	private JTextField nameField = null;
	private JTextField surnameTextField = null;
	private JTextField birthDateField = null;

	private JComboBox<String> userTypeComboBox = null;
	private JButton cancelBtn = null;
	private JButton confirmBtn = null;
	private JTextField confirmPassField = null;

	public RegisterPannel(List<JPanel> pannels) {
		setBounds(0, 0, 1215, 666);
		setLayout(null);
		setBackground(new Color(57, 57, 57));

		lblNewLabel = new JLabel("Logo aqui");
		lblNewLabel.setBounds(38, 27, 87, 87);
		add(lblNewLabel);

		lblRegisterEmail = new JLabel("E-mail");
		lblRegisterEmail.setBounds(664, 220, 86, 43);
		lblRegisterEmail.setForeground(new Color(255, 255, 255));
		lblRegisterEmail.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		add(lblRegisterEmail);

		lblRegisterName = new JLabel("Nombre");
		lblRegisterName.setBounds(141, 228, 86, 27);
		lblRegisterName.setForeground(new Color(255, 255, 255));
		lblRegisterName.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		add(lblRegisterName);

		lblRegisterSurname = new JLabel("Primer apellido");
		lblRegisterSurname.setBounds(141, 266, 176, 32);
		lblRegisterSurname.setForeground(new Color(255, 255, 255));
		lblRegisterSurname.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		add(lblRegisterSurname);

		lblRegisterPasswd = new JLabel("Contraseña");
		lblRegisterPasswd.setBounds(357, 475, 159, 31);
		lblRegisterPasswd.setForeground(new Color(255, 255, 255));
		lblRegisterPasswd.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		add(lblRegisterPasswd);

		lblConfirmPasswd = new JLabel("Confirmar contraseña");
		lblConfirmPasswd.setBounds(357, 517, 230, 27);
		lblConfirmPasswd.setForeground(new Color(255, 255, 255));
		lblConfirmPasswd.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		add(lblConfirmPasswd);

		lblRegisterBirthDate = new JLabel("Fecha de Nacimiento");
		lblRegisterBirthDate.setBounds(659, 266, 187, 32);
		lblRegisterBirthDate.setForeground(new Color(255, 255, 255));
		lblRegisterBirthDate.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		add(lblRegisterBirthDate);

		datePicker = new JDatePickerImpl(new JDatePanelImpl(null));
		datePicker.setBounds(850, 266, 230, 32);

		datePicker.addActionListener(e -> {
			GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
			selectedDate = calendar.getTime();
		});
		add(datePicker);

		emailTextField = new JTextField();
		emailTextField.setBounds(738, 230, 288, 20);
		add(emailTextField);
		emailTextField.setColumns(10);

		nameField = new JTextField();
		nameField.setBounds(235, 229, 268, 20);
		add(nameField);
		nameField.setColumns(10);

		surnameTextField = new JTextField();
		surnameTextField.setBounds(285, 269, 218, 20);
		add(surnameTextField);
		surnameTextField.setColumns(10);

		userTypeComboBox = new JComboBox<String>();
		userTypeComboBox.setBounds(491, 554, 239, 29);
		userTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Cliente", "Entrenador" }));
		add(userTypeComboBox);

		lblRegisterUserType = new JLabel("Tipo de Usuario");
		lblRegisterUserType.setBounds(357, 553, 176, 31);
		lblRegisterUserType.setForeground(new Color(255, 255, 255));
		lblRegisterUserType.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		add(lblRegisterUserType);

		registerTitleLbl = new JLabel("REGISTRATE");
		registerTitleLbl.setBounds(401, 11, 349, 64);
		registerTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		registerTitleLbl.setForeground(new Color(190, 30, 255));
		registerTitleLbl.setFont(new Font("Segoe UI Black", Font.BOLD, 41));
		add(registerTitleLbl);

		cancelBtn = new JButton("Cancelar");
		cancelBtn.setBounds(16, 622, 98, 33);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pannels.get(0).setVisible(true);
				pannels.get(1).setVisible(false);
				pannels.get(2).setVisible(false);
				pannels.get(3).setVisible(false);
				pannels.get(4).setVisible(false);
				pannels.get(5).setVisible(false);
			}

		});
		add(cancelBtn);

		confirmBtn = new JButton("Confirmar");
		confirmBtn.setBounds(1107, 622, 98, 33);
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkRegister()) {
						pannels.get(0).setVisible(true);
						pannels.get(1).setVisible(false);
						pannels.get(2).setVisible(false);
						pannels.get(3).setVisible(false);
						pannels.get(4).setVisible(false);
						pannels.get(5).setVisible(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(confirmBtn);

		passwordField = new JTextField();
		passwordField.setBounds(467, 483, 263, 20);
		add(passwordField);

		confirmPassField = new JTextField();
		confirmPassField.setBounds(543, 519, 187, 20);
		add(confirmPassField);

		personalInfoTitle = new JLabel("DATOS PERSONALES");
		personalInfoTitle.setBounds(345, 128, 365, 64);
		personalInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
		personalInfoTitle.setForeground(new Color(190, 30, 255));
		personalInfoTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		add(personalInfoTitle);

		lblCountOpt = new JLabel("DATOS DE LA CUENTA");
		lblCountOpt.setBounds(348, 376, 365, 64);
		lblCountOpt.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountOpt.setForeground(new Color(190, 30, 255));
		lblCountOpt.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		add(lblCountOpt);

	}

	private boolean checkRegister() throws Exception {
		if (areAllEmpty()) {
			User newUser = createUser();
			return new RegisterController().registerUser(newUser);
		} else {
			JOptionPane.showMessageDialog(null, "Hay campos no completos", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	private User createUser() {
		boolean trainer = false;
		List<Workout> workouts = new ArrayList<Workout>();
		if (userTypeComboBox.getSelectedIndex() == 0) {
			trainer = false;
		} else if (userTypeComboBox.getSelectedIndex() == 1) {
			trainer = true;
		}
		User user = new User();
		user.setName(nameField.getText());
		user.setPassword(passwordField.getText());
		user.setMail(emailTextField.getText());
		user.setSurname(surnameTextField.getText());
		user.setBirthDate(selectedDate);
		user.setTrainer(trainer);
		user.setWorkouts(workouts);
		return user;
	}

	private boolean areAllEmpty() {
		if (!nameField.getText().isEmpty() && !surnameTextField.getText().isEmpty()
				&& !passwordField.getText().isEmpty() && !confirmPassField.getText().isEmpty() && null != selectedDate
				&& !emailTextField.getText().isEmpty())
			return true;
		else
			return false;
	}
}
