package main.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import main.manager.ExerciseManager;
import main.manager.pojo.Exercise;

public class ExerciseController {

	public List<Exercise> getAllExercises() throws Exception {
		return new ExerciseManager().getAll();
	}

	public List<Exercise> getExercisesForWorkout(String workoutId)
			throws ExecutionException, InterruptedException, Exception {
		return new ExerciseManager().getExercisesForWorkout(workoutId);
	}

	public List<Exercise> getExercisesFromWorkout(int userLevel) throws Exception {
		return new ExerciseManager().getExercisesWithLevel(userLevel);
	}

}
