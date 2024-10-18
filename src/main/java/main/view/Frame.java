package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.view.pannels.ExercisePannel;
import main.view.pannels.HistoricPannel;
import main.view.pannels.LoginPannel;
import main.view.pannels.ProfilePannel;
import main.view.pannels.RegisterPannel;
import main.view.pannels.WorkoutsPannel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;

	private List<JPanel> pannels = null;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			Frame frame = new Frame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Frame() {

		pannels = new ArrayList<JPanel>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1230, 700);
		setResizable(false);
		setTitle("GYMAPP G2");
		setLocationRelativeTo(null);

		contentPanel = new JPanel();

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		LoginPannel loginPannel = new LoginPannel(pannels);
		loginPannel.setVisible(true);
		contentPanel.add(loginPannel);
		pannels.add(loginPannel);

		RegisterPannel registerPannel = new RegisterPannel(pannels);
		registerPannel.setVisible(false);
		contentPanel.add(registerPannel);
		pannels.add(registerPannel);

		WorkoutsPannel workoutPannel = new WorkoutsPannel(pannels);
		workoutPannel.setVisible(false);
		contentPanel.add(workoutPannel);
		pannels.add(workoutPannel);

		ProfilePannel profilePannel = new ProfilePannel(pannels);
		profilePannel.setVisible(false);
		contentPanel.add(profilePannel);
		pannels.add(profilePannel);

		HistoricPannel historicPannel = new HistoricPannel(pannels);
		historicPannel.setVisible(false);
		contentPanel.add(historicPannel);
		pannels.add(historicPannel);

		ExercisePannel exercisePannel = new ExercisePannel(pannels);
		exercisePannel.setVisible(false);
		contentPanel.add(exercisePannel);
		pannels.add(exercisePannel);

	}
}
