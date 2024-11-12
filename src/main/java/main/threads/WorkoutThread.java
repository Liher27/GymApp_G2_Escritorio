package main.threads;

import java.util.List;

import javax.swing.SwingUtilities;

import main.manager.pojo.Exercise;
import main.view.pannels.ExercisePannel;

public class WorkoutThread extends Thread {

	private boolean stopped = true;
	private ExercisePannel exercisePannel;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;
	private ExerciseThread exeThread;
	private long elapsed = 0;

	public WorkoutThread(String name, List<Exercise> exercises, ExercisePannel exercisePannel) {
		super(name);
		this.exercisePannel = exercisePannel;
	}

	@Override
	public void run() {
		// Esto para que yifey?
		// exeThread = new ExerciseThread("exercises", exercises, exercisePannel);
		//exeThread.start();
		startWorkout();
		try {
			exeThread.join();
			System.out.println();
			pauseTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void startWorkout() {
		while (true) {
			if (!stopped) {
				elapsed = (System.currentTimeMillis() - programStart - pauseCount) / 1000;
				SwingUtilities.invokeLater(() -> {
					exercisePannel.loadWorkoutTime(format(elapsed));
				});
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
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
		stopped = true;
		exercisePannel.resetWorkoutTime();
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
