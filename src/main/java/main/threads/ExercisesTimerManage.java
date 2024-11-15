package main.threads;

import javax.swing.SwingUtilities;

import main.view.pannels.ExercisePannel;

public class ExercisesTimerManage extends Thread {

	private volatile boolean stopped = true;
	private boolean isPaused = false;
	private ExercisePannel exercisePannel;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;
	private long elapsed = 0;

	public ExercisesTimerManage(String name, ExercisePannel exercisePannel) {
		super(name);
		this.exercisePannel = exercisePannel;
	}
	
	public void run() {
		startExercise();
	}
	
	private void startExercise() {
		stopped = false;
		new Thread(() -> {
			while (!stopped) {
				if (!isPaused) {
					elapsed = (System.currentTimeMillis() - programStart - pauseCount) / 1000;
					SwingUtilities.invokeLater(() -> {
						exercisePannel.loadExerciseTime(format(elapsed));
					});
				} else {

				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		}).start();
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

	public void stopTimer() {
		pauseStart = programStart;
		pauseCount = 0;
		stopped = true;
		exercisePannel.resetWorkoutTime();

	}
	
	public void pauseTime() {
	    if (isPaused) {
	        pauseCount += (System.currentTimeMillis() - pauseStart);
	        isPaused = false;
	    } else {
	        pauseStart = System.currentTimeMillis();
	        System.out.println(pauseStart);
	        isPaused = true;
	    }
	}

}
