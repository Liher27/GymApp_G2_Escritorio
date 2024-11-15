package main.backups;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

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

	public void getBackup() throws IOException, InterruptedException, ClassNotFoundException {
		ProcessBuilder processBuilder = new ProcessBuilder(processName, "/C", readCommand);
		Process process = processBuilder.start();

		ObjectInputStream objectInputStream = new ObjectInputStream(process.getInputStream());

		workouts = (List<Workout>) objectInputStream.readObject();

		StatusSingleton.getInstance().setBackupedWorkouts(workouts);

		objectInputStream.close();

	}

	private void writeDocuments() throws Exception {
		workoutController = new WorkoutController();
		exerciseController = new ExerciseController();
		user = StatusSingleton.getInstance().getUser();
		workouts = workoutController.getWorkoutsForUserLevel(user.getUserLevel());
		for (Workout workout : workouts) {
			exercises = exerciseController.getExercisesForWorkout(workout.getWorkoutUID());
			workout.setExercises(exercises);
		}
		writeWorkouts(workouts);
	}

	private void writeWorkouts(List<Workout> workouts) throws IOException {
		File file = new File(workoutFileName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = null;

		objectOutputStream = new ObjectOutputStream(fileOutputStream);

		objectOutputStream.writeObject(workouts);
		
		objectOutputStream.close();
		fileOutputStream.close();
	}
}
