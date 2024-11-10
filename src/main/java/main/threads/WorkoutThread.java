package main.threads;

import javax.swing.SwingUtilities;

import main.view.pannels.ExercisePannel;

public class WorkoutThread extends Thread {
	private boolean stopped = true;
	private ExercisePannel exercisePannel;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;

	public WorkoutThread(String name, ExercisePannel exercisePannel) {
		super(name);
		this.exercisePannel = exercisePannel;
		setDaemon(true);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			if (!stopped) {
				long elapsed = (System.currentTimeMillis() - programStart - pauseCount) /1000;
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
	            resumeTimer();
	            exercisePannel.changeExerciseButtonText();
	        }
	    }
	

}
