package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.manager.StatusSingleton;
import main.view.pannels.ExercisePannel;
import main.view.pannels.HistoricPannel;
import main.view.pannels.LoginPannel;
import main.view.pannels.ProfilePannel;
import main.view.pannels.RegisterPannel;
import main.view.pannels.WorkoutsPannel;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;


	/**
	 * Create the frame.
	 */
	public Frame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1230, 700);
		setResizable(false);
		setTitle("GYMAPP G2");
		setLocationRelativeTo(null);

		contentPanel = new JPanel();

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		// 0
		LoginPannel loginPannel = new LoginPannel();
		loginPannel.setVisible(true);
		contentPanel.add(loginPannel);
		add(loginPannel);
		StatusSingleton.getInstance().setLoginPannel(loginPannel);

		// 1
		RegisterPannel registerPannel = new RegisterPannel();
		registerPannel.setVisible(false);
		contentPanel.add(registerPannel);
		add(registerPannel);
		StatusSingleton.getInstance().setRegisterPannel(registerPannel);

		// 2
		WorkoutsPannel workoutPannel = new WorkoutsPannel();
		workoutPannel.setVisible(false);
		contentPanel.add(workoutPannel);
		add(workoutPannel);
		StatusSingleton.getInstance().setWorkoutsPannel(workoutPannel);

		// 3
		ProfilePannel profilePannel = new ProfilePannel();
		profilePannel.setVisible(false);
		contentPanel.add(profilePannel);
		add(profilePannel);
		StatusSingleton.getInstance().setProfilePannel(profilePannel);

		// 4
		HistoricPannel historicPannel = new HistoricPannel();
		historicPannel.setVisible(false);
		contentPanel.add(historicPannel);
		add(historicPannel);
		StatusSingleton.getInstance().setHistoricPannel(historicPannel);

		// 5
		ExercisePannel exercisePannel = new ExercisePannel();
		exercisePannel.setVisible(false);
		contentPanel.add(exercisePannel);
		add(exercisePannel);
		StatusSingleton.getInstance().setExercisePannel(exercisePannel);
	}
}
