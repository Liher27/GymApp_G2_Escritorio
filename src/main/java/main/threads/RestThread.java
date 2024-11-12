package main.threads;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import main.manager.pojo.Exercise;
import main.view.pannels.ExercisePannel;

public class RestThread extends Thread {
	private boolean stopped = true;
	private ExercisePannel exercisePannel;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;
	private List<Exercise> exercises;
	private ExerciseThread exerciseThread;

	public RestThread(String name, List<Exercise> exercises, ExercisePannel exercisePannel,
			ExerciseThread exerciseThread) {
		super(name);
		this.exercisePannel = exercisePannel;
		this.exercises = exercises;
		this.exerciseThread = exerciseThread;
	}

	@Override
	public void run() {
		startRest();
	}

	private void startRest() {
		for (Exercise exercise : exercises) {
			int restTime = exercise.getRestTime();
			countdown(restTime);
			exerciseThread.pauseTime();
			try {
				sleep(restTime * 1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			exerciseThread.resumeTimer();
		}
	}

	public void countdown(int restTime) {
		int remainingTime = restTime;
		while (remainingTime >= 0) {
			int finalRemainingTime = remainingTime;

			SwingUtilities.invokeLater(() -> exercisePannel.loadRestTime(finalRemainingTime));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}

			remainingTime--;
		}
		SwingUtilities.invokeLater(() -> {
			exercisePannel.loadRestTime(0);
			JOptionPane.showMessageDialog(null, "Tiempo de descanso terminado", null, JOptionPane.ERROR_MESSAGE);
		});
	}

	public void pauseRestTimer() {
		stopped = true;
	}

	public void resumeRestTimer() {
		stopped = false;
		programStart = System.currentTimeMillis() - pauseCount;
	}

	public void stopRestTimer() {
		pauseStart = programStart;
		pauseCount = 0;
		stopped = true;
		exercisePannel.resetRestTimer();
	}

	public void pauseTime() {
		if (!stopped) {
			pauseStart = System.currentTimeMillis();
			stopped = true;
		} else {
			pauseCount += (System.currentTimeMillis() - pauseStart);
			stopped = false;
		}
	}
}
