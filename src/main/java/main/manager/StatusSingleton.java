package main.manager;

import main.manager.pojo.User;
import main.view.pannels.ExercisePannel;
import main.view.pannels.HistoricPannel;
import main.view.pannels.LoginPannel;
import main.view.pannels.ProfilePannel;
import main.view.pannels.RegisterPannel;
import main.view.pannels.WorkoutsPannel;

public class StatusSingleton {

	private static StatusSingleton statusSingleton = null;

	private ExercisePannel exercisePannel = new ExercisePannel();
	private HistoricPannel historicPannel = new HistoricPannel();
	private LoginPannel loginPannel = new LoginPannel();
	private ProfilePannel profilePannel = new ProfilePannel();
	private RegisterPannel registerPannel = new RegisterPannel();
	private WorkoutsPannel workoutsPannel = new WorkoutsPannel();
	private User user = new User();

	public static StatusSingleton getInstance() {
		if (null == statusSingleton) {
			statusSingleton = new StatusSingleton();
		}
		return statusSingleton;
	}

	public ExercisePannel getExercisePannel() {
		return exercisePannel;
	}

	public void setExercisePannel(ExercisePannel exercisePannel) {
		this.exercisePannel = exercisePannel;
	}

	public HistoricPannel getHistoricPannel() {
		return historicPannel;
	}

	public void setHistoricPannel(HistoricPannel historicPanel) {
		this.historicPannel = historicPanel;
	}

	public LoginPannel getLoginPannel() {
		return loginPannel;
	}

	public void setLoginPannel(LoginPannel loginPannel) {
		this.loginPannel = loginPannel;
	}

	public ProfilePannel getProfilePannel() {
		return profilePannel;
	}

	public void setProfilePannel(ProfilePannel profilePannel) {
		this.profilePannel = profilePannel;
	}

	public WorkoutsPannel getWorkoutsPannel() {
		return workoutsPannel;
	}

	public void setWorkoutsPannel(WorkoutsPannel workoutsPannel) {
		this.workoutsPannel = workoutsPannel;
	}

	public RegisterPannel getRegisterPannel() {
		return registerPannel;
	}

	public void setRegisterPannel(RegisterPannel registerPannel) {
		this.registerPannel = registerPannel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void changeToLoginPannel() {
		getLoginPannel().setVisible(true);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(false);
		getHistoricPannel().setVisible(false);
		getProfilePannel().setVisible(false);
	}

	public void changeToRegisterPannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(true);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(false);
		getHistoricPannel().setVisible(false);
		getProfilePannel().setVisible(false);
	}

	public void changeToWorkoutsPannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(true);
		getExercisePannel().setVisible(false);
		getHistoricPannel().setVisible(false);
		getProfilePannel().setVisible(false);
	}

	public void changeToExercisePannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(true);
		getHistoricPannel().setVisible(false);
		getProfilePannel().setVisible(false);
	}

	public void changeToHistoricPannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(false);
		getHistoricPannel().setVisible(true);
		getProfilePannel().setVisible(false);
	}

	public void changeToProfilePannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(false);
		getHistoricPannel().setVisible(false);
		getProfilePannel().setVisible(true);
	}

}
