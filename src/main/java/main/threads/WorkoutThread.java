package main.threads;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.SwingUtilities;

import main.manager.StatusSingleton;
import main.manager.pojo.Exercise;
import main.manager.pojo.Historic;
import main.manager.pojo.Workout;
import main.view.pannels.ExercisePannel;

public class WorkoutThread extends Thread {
	private volatile  boolean stopped = true;
	private ExercisePannel exercisePannel;
	private long programStart = System.currentTimeMillis();
	private long pauseStart = programStart;
	private long pauseCount = 0;
	private ExerciseThread exeThread;
	private List<Exercise> exercises;
	private long elapsed = 0;
	private Workout workout = null;
	private ExerciseThread exerciseThread;

	public WorkoutThread(String name, List<Exercise> exercises, ExercisePannel exercisePannel,ExerciseThread exerciseThread) {
		super(name);
		this.exercisePannel = exercisePannel;
		this.exercises = exercises;
		this.exerciseThread = exerciseThread;
	}

	@Override
	public void run() {
		startWorkout();
		exeThread = new ExerciseThread("exercises", exercises, exercisePannel,this);
		exeThread.start();
		
		try {
			exeThread.join();
			System.out.println();
			pauseTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pauseWorkoutTimer();
		
		
	}

	private void startWorkout() {
        stopped = false;
        new Thread(() -> {  
            programStart = System.currentTimeMillis();
            while (!stopped) {
                elapsed = (System.currentTimeMillis() - programStart - pauseCount) / 1000;
                SwingUtilities.invokeLater(() -> {
                    exercisePannel.loadWorkoutTime(format(elapsed));
                });
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

	public void resumeTimer() {
		stopped = false;
		programStart = System.currentTimeMillis() - pauseCount;
	}
	

	public void stopTimer() {
		pauseStart = programStart;
		pauseCount = 0;
		stopped = true;
		exercisePannel.resetWorkoutTime();
		exeThread.interrupt();
	
	}
	
	public Historic getLastWorkoutInfo() {
	    pauseWorkoutTimer();
	    Historic historic = new Historic();

	    workout = StatusSingleton.getInstance().getWorkout();
	    LocalDate currentDate = LocalDate.now();  
	    Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    int intvalue = (int)elapsed;
	    double percentage = ((double) intvalue / 200) * 100;
	
	    String percentageString = String.format("%.2f", percentage);

	    historic.setWorkoutName(workout.getWorkoutName());
	    historic.setLevel(workout.getLevel());
	    historic.setTotalTime(intvalue);
	    historic.setProvidedTime(200);
	    historic.setFinishDate(date);
	    historic.setExercisePercent(percentageString +"%");

	    System.out.println("Formatted percentage: " + percentageString);
	    return historic;
	}
	public void pauseWorkoutTimer() {
        stopped = true;
        pauseCount += System.currentTimeMillis() - programStart; 
    }
	   public void pauseTime() {
	        if (!stopped) {
	            pauseStart = System.currentTimeMillis();  
	            stopped = true;
	        } else {
	            pauseCount += (System.currentTimeMillis() - pauseStart);  
                stopped = false;  
	        }
	        exeThread.pauseTime();
	    }
	
	

}
