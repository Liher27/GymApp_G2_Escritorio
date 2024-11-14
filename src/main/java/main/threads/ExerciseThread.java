package main.threads;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import main.manager.StatusSingleton;
import main.manager.UserManager;
import main.manager.pojo.Exercise;
import main.manager.pojo.User;
import main.view.pannels.ExercisePannel;

public class ExerciseThread extends Thread {
	private volatile boolean stopped = true;
	private ExercisePannel exercisePannel;
	private WorkoutThread workoutThread;
	private List<Exercise> exercises;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;
	private long currentSecond = 0;
	private int[] count = { 0, 1, 2, 3, 4, 5 };
	private User user;

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
			}
			contador++;

		}
		user = StatusSingleton.getInstance().getUser();
	    user.setUserLevel(user.getUserLevel() + 1);

	    try {
	        UserManager userManager = new UserManager();
	        if (!userManager.modify(user)) {
	        	JOptionPane.showMessageDialog(null, "no se ha cobectado a la base datos", "Error!",
						JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "No se ha actualizado el user level", "Error!",
					JOptionPane.ERROR_MESSAGE);
	    }
		workoutThread.pauseWorkoutTimer();
		stopTimer();

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
		if (stopped) {
			pauseCount += (System.currentTimeMillis() - pauseStart);
			stopped = false;
		} else {
			pauseStart = System.currentTimeMillis();
			stopped = true;
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
		JOptionPane.showMessageDialog(null, "Tiempo de descanso terminado");
	}
}
