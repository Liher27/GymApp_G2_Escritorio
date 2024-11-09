package main.backups;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import main.controller.ExerciseController;
import main.controller.WorkoutController;
import main.manager.StatusSingleton;
import main.manager.pojo.Exercise;
import main.manager.pojo.User;
import main.manager.pojo.Workout;

public class BackupMaker extends AbstractBackupMaker {

	private User user = null;
	private List<Workout> workouts = null;
	private WorkoutController workoutController = null;
	private List<Exercise> exercises = null;
	private ExerciseController exerciseController = null;

	public void doBackup() throws Exception {
		writeDocuments();
	}

	public void getBackup() throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(processName, "/C", readCommand);
		Process process = processBuilder.start();

		int exitCode = process.waitFor();
		if (exitCode != 0) {
			System.out.println("Bien!!!");
		} else {
			System.out.println(new IOException().getMessage());
		}

	}

	private void writeDocuments() throws Exception {
		workoutController = new WorkoutController();
		exerciseController = new ExerciseController();
		user = StatusSingleton.getInstance().getUser();
		workouts = workoutController.getAllWorkouts();
		for (Workout workout : workouts) {
			exercises = exerciseController.getExercisesForWorkout(workout.getWorkoutUID());
			workout.setExercises(exercises);
		}
		writeUser(user);
		writeWorkouts(workouts);
	}

	private void writeWorkouts(List<Workout> workouts) throws IOException {
		File file = new File(workoutFileName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = null;

		objectOutputStream = new ObjectOutputStream(fileOutputStream);

		for (Workout workout : workouts) {
			objectOutputStream.writeObject(workout);
		}

		objectOutputStream.close();
		fileOutputStream.close();
	}

	private void writeUser(User user) throws IOException {
		File file = new File(userFileName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

		objectOutputStream.writeObject(user);

		objectOutputStream.close();
		fileOutputStream.close();
	}
}
