package main.manager;

import java.util.List;

import main.manager.pojo.Exercise;
import main.manager.pojo.User;
import main.manager.pojo.Workout;
import main.view.pannels.ExercisePannel;
import main.view.pannels.HistoricPanel;
import main.view.pannels.LoginPannel;
import main.view.pannels.ProfilePannel;
import main.view.pannels.RegisterPannel;
import main.view.pannels.WorkoutsPannel;

public class StatusSingleton {

	private static StatusSingleton statusSingleton = null;
	private ExercisePannel exercisePannel = null;
	private LoginPannel loginPannel = null;
	private ProfilePannel profilePannel = null;
	private RegisterPannel registerPannel = null;
	private WorkoutsPannel workoutsPannel = null;
	private HistoricPanel historicPannel = null;
	private User user = null;
	private Workout workout = null;
	private Exercise exercise = null;
	private int selectedRow = 0;
	private List<Workout> backupedWorkouts = null;
	public boolean offline = false;
	public double percent = 0;

	public StatusSingleton() {
		exercisePannel = new ExercisePannel();
		loginPannel = new LoginPannel();
		profilePannel = new ProfilePannel();
		registerPannel = new RegisterPannel();
		workoutsPannel = new WorkoutsPannel();
		historicPannel = new HistoricPanel();
		user = new User();
		workout = new Workout();
		exercise = new Exercise();
	}
	
	public double getPercent() {
		return percent;
	}



	public void setPercent(double percent) {
		this.percent = percent;
	}



	public List<Workout> getBackupedWorkouts() {
		return backupedWorkouts;
	}

	public void setBackupedWorkouts(List<Workout> backupedWorkouts) {
		this.backupedWorkouts = backupedWorkouts;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public HistoricPanel getHistoryPannel() {
		return historicPannel;
	}

	public void setHistoryPannel(HistoricPanel historicPannel) {
		this.historicPannel = historicPannel;
	}

	public static StatusSingleton getInstance() {
		if (null == statusSingleton) {
			statusSingleton = new StatusSingleton();
		}
		return statusSingleton;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public ExercisePannel getExercisePannel() {
		return exercisePannel;
	}

	public void setExercisePannel(ExercisePannel exercisePannel) {
		this.exercisePannel = exercisePannel;
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
		getProfilePannel().setVisible(false);
		getHistoryPannel().setVisible(false);

	}

	public void changeToRegisterPannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(true);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(false);
		getProfilePannel().setVisible(false);
		getHistoryPannel().setVisible(false);
	}

	public void changeToWorkoutsPannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(true);
		getExercisePannel().setVisible(false);
		getProfilePannel().setVisible(false);
		getHistoryPannel().setVisible(false);
	}

	public void changeToExercisePannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(true);
		getProfilePannel().setVisible(false);
		getHistoryPannel().setVisible(false);
	}

	public void changeToHistoricPannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(false);
		getHistoryPannel().setVisible(true);
		getProfilePannel().setVisible(false);
	}

	public void changeToProfilePannel() {
		getLoginPannel().setVisible(false);
		getRegisterPannel().setVisible(false);
		getWorkoutsPannel().setVisible(false);
		getExercisePannel().setVisible(false);
		getProfilePannel().setVisible(true);
		getHistoryPannel().setVisible(false);
	}

}
