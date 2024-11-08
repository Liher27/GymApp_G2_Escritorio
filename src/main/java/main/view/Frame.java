package main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.manager.StatusSingleton;
import main.view.pannels.ExercisePannel;
import main.view.pannels.HistoricPannel;
import main.view.pannels.HistoryPanel;
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
		setBounds(0, 0, 1250, 700);
		setResizable(false);
		setTitle("GYMAPP G2");
		setLocationRelativeTo(null);

		contentPanel = new JPanel();

		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		// 0
		LoginPannel loginPannel = StatusSingleton.getInstance().getLoginPannel();
		loginPannel.setVisible(true);
		contentPanel.add(loginPannel);
		add(loginPannel);

		// 1
		RegisterPannel registerPannel = StatusSingleton.getInstance().getRegisterPannel();
		registerPannel.setVisible(false);
		contentPanel.add(registerPannel);
		add(registerPannel);

		// 2
		WorkoutsPannel workoutPannel = StatusSingleton.getInstance().getWorkoutsPannel();
		workoutPannel.setVisible(false);
		contentPanel.add(workoutPannel);
		add(workoutPannel);

		// 3
		ProfilePannel profilePannel = StatusSingleton.getInstance().getProfilePannel();
		profilePannel.setVisible(false);
		contentPanel.add(profilePannel);
		add(profilePannel);

		// 4
		HistoryPanel historyPannel = StatusSingleton.getInstance().getHistoryPannel();
		historyPannel.setVisible(false);
		contentPanel.add(historyPannel);
		add(historyPannel);

		// 5
		ExercisePannel exercisePannel = StatusSingleton.getInstance().getExercisePannel();
		exercisePannel.setVisible(false);
		contentPanel.add(exercisePannel);
		add(exercisePannel);
	}
}
