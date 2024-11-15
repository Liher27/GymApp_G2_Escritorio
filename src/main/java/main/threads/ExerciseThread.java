package main.threads;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import main.controller.UserController;
import main.manager.StatusSingleton;
import main.manager.UserManager;
import main.manager.pojo.Exercise;
import main.manager.pojo.User;
import main.view.pannels.ExercisePannel;

public class ExerciseThread extends Thread {
	private volatile boolean stopped = true;
	private boolean isPaused = false;
	private ExercisePannel exercisePannel;
	private List<Exercise> exercises;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;
	private long currentSecond = 0;
	private int[] count = { 0, 1, 2, 3, 4, 5 };
	private WorkoutThread workoutThread;
	private User user;
	private UserController userController;

	public ExerciseThread(String name, List<Exercise> exercises, ExercisePannel exercisePannel,
			WorkoutThread workoutThread) {
		super(name);
		this.exercisePannel = exercisePannel;
		this.exercises = exercises;
		this.workoutThread = workoutThread;

	}

	@Override
	public void run() {
		workoutThread = new WorkoutThread(getName(), exercises, exercisePannel, null);
		startExercise();
	}

	public void startExercise() {
		int contador = 0;
		while (contador < this.exercises.size()) {
			fiveSCount();
			int serieSet = this.exercises.get(contador).getSeriesNumber();
			for (int i = 0; i < serieSet; i++) {
				stopped = false;
				programStart = System.currentTimeMillis();
				currentSecond = 0;
				while (!stopped) {
					if (isPaused) {
						try {
							Thread.sleep(100);
							continue;
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							return;
						}
					}
					currentSecond = (System.currentTimeMillis() - programStart - pauseCount) / 1000;
					SwingUtilities.invokeLater(() -> exercisePannel.loadExerciseTime(format(currentSecond)));
					exercisePannel.loadExerciseNameAndSerie(this.exercises.get(contador).getExerciseName(), (i + 1));
					if (currentSecond >= 4) {
						stopped = true;
						exercisePannel.resetExerciseTime();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
				}
				stopped = false;
				setRestTime(exercises.get(contador).getRestTime());
				double percentage = ((double) (contador + 1) / this.exercises.size()) * 100;
				StatusSingleton.getInstance().setPercent(percentage);
				
			}

			contador++;
		}
		int workoutLevel = StatusSingleton.getInstance().getWorkout().getLevel();
		int currentUserLevel = StatusSingleton.getInstance().getUser().getUserLevel();
		if (workoutLevel == currentUserLevel) {
			try {
				user = StatusSingleton.getInstance().getUser();
				user.setUserLevel(user.getUserLevel() + 1);
				userController = new UserController();
				if (!userController.modify(user)) {
					JOptionPane.showMessageDialog(null, "no se ha coectado a la base datos", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se ha actualizado el user level", "Error!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		stopTimer();
		workoutThread.pauseTime();
	}

	private String format(long elapsed) {
		int hour, minute, second;
		second = (int) (elapsed % 60);
		elapsed = elapsed / 60;
		minute = (int) (elapsed % 60);
		elapsed = elapsed / 60;
		hour = (int) (elapsed % 60);
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}

	public void resumeTimer() {
		stopped = false;
		programStart = System.currentTimeMillis() - pauseCount;
	}

	public void stopTimer() {
		pauseStart = programStart;
		pauseCount = 0;
		stopped = false;
		exercisePannel.resetExerciseTime();
	}

	public void pauseTime() {
		if (isPaused) {
			pauseCount += (System.currentTimeMillis() - pauseStart);
			isPaused = false;
		} else {
			pauseStart = System.currentTimeMillis();
			isPaused = true;
		}
	}

	public void fiveSCount() {
		for (int i = count.length - 1; i >= 0; i--) {
			try {
				Thread.sleep(1000);
				exercisePannel.setCountDown(count[i]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setRestTime(int restTime) {
		int remainingTime = restTime;
		while (remainingTime >= 0) {
			int finalRemainingTime = remainingTime;
			exercisePannel.loadRestTime(finalRemainingTime);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				exercisePannel.loadRestTime(0);
				return;
			}
			remainingTime--;
		}
		exercisePannel.loadRestTime(0);
		exercisePannel.userUsedRestTime(restTime);
		JOptionPane.showMessageDialog(null, "Tiempo de descanso terminado");
	}
}
