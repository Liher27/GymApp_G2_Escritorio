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
		setBounds(100, 100, 1278, 719);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		// 0
		LoginPannel loginPannel = new LoginPannel(pannels);
		loginPannel.setVisible(false);
		contentPanel.add(loginPannel);
		pannels.add(loginPannel);

		// 1
		RegisterPannel registerPannel = new RegisterPannel(pannels);
		registerPannel.setVisible(false);
		contentPanel.add(registerPannel);
		pannels.add(registerPannel);

		// 2
		WorkoutsPannel workoutPannel = new WorkoutsPannel(pannels);
		workoutPannel.setVisible(false);
		contentPanel.add(workoutPannel);
		pannels.add(workoutPannel);

		// 3
		ProfilePannel profilePannel = new ProfilePannel(pannels);
		profilePannel.setVisible(false);
		contentPanel.add(profilePannel);
		pannels.add(profilePannel);

		// 4
		HistoricPannel historicPannel = new HistoricPannel(pannels);
		historicPannel.setVisible(false);
		contentPanel.add(historicPannel);
		pannels.add(historicPannel);

		// 5
		ExercisePannel exercisePannel = new ExercisePannel(pannels);
		exercisePannel.setVisible(false);
		contentPanel.add(exercisePannel);
		pannels.add(exercisePannel);

	}
}
