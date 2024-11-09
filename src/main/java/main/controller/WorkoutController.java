package main.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import main.manager.WorkoutManager;
import main.manager.pojo.Workout;

public class WorkoutController {

	public List<Workout> getAllWorkouts() throws Exception {
		return new WorkoutManager().getAll();
	}

	public List<Workout> getWorkoutsForUserLevel(int userLevel)
			throws ExecutionException, InterruptedException, Exception {
		return new WorkoutManager().getWorkoutsForUserLevel(userLevel);
	}

	public Workout getOneWorkout(Workout workout) throws Exception {
		return new WorkoutManager().getOne(workout);
	}

}