package main.threads;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import main.manager.pojo.Exercise;
import main.view.pannels.ExercisePannel;

public class ExerciseThread extends Thread {
	private boolean stopped = true;
	private ExercisePannel exercisePannel;
	private List<Exercise> exercises;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;
	private long currentSecond = 0;

	public ExerciseThread(String name, List<Exercise> exercises, ExercisePannel exercisePannel) {
		super(name);
		this.exercisePannel = exercisePannel;
		this.exercises = exercises;
	}

	@Override
	public void run() {
			startExercise();
	}

	public void startExercise() {
		int contador = 0;
		while (contador < this.exercises.size()) {
			int serieSet = this.exercises.get(contador).getSeriesNumber();
			for (int i = 0; i < serieSet; i++) {
				stopped = false;
				programStart = System.currentTimeMillis();

				currentSecond = 0;

				while (!stopped) {
					currentSecond = (System.currentTimeMillis() - programStart - pauseCount) / 1000;
					SwingUtilities.invokeLater(() -> exercisePannel.loadExerciseTime(format(currentSecond)));
					exercisePannel.loadExerciseNameAndSerie(this.exercises.get(contador).getExerciseName(),(i+1));
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
			}
			contador++;
		}

		JOptionPane.showMessageDialog(null,"Has terminado todos los ejercicios");
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
		stopped = true;
		exercisePannel.resetExerciseTime();
	}

	public void pauseTime() {
		if (!stopped ) {
			pauseCount += (System.currentTimeMillis() - pauseStart);
			stopped = false;
			
		} else {
			pauseStart = System.currentTimeMillis();
			stopped = true;
			exercisePannel.changeExerciseButtonText();
		}
	}

}
