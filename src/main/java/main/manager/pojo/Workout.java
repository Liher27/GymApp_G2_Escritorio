package main.manager.pojo;

import java.util.List;
import java.util.Objects;

public class Workout {

	
	private String workoutUID = null;
	private int level = 0;
	private String workoutName = null;
	private int exerciseNumber = 0;
	private String video = null;
	private List<Exercise> exercises;

	public String getWorkoutUID() {
		return workoutUID;
	}

	public void setWorkoutUID(String workoutUID) {
		this.workoutUID = workoutUID;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public int getExerciseNumber() {
		return exerciseNumber;
	}

	public void setExerciseNumber(int exerciseNumber) {
		this.exerciseNumber = exerciseNumber;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Override
	public String toString() {
		return "Workout [level=" + level + ", workoutName=" + workoutName + ", exerciseNumber=" + exerciseNumber
				+ ", video=" + video + ", exercises=" + exercises + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(exerciseNumber, exercises, level, video, workoutName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workout other = (Workout) obj;
		return exerciseNumber == other.exerciseNumber && Objects.equals(exercises, other.exercises)
				&& level == other.level && Objects.equals(video, other.video)
				&& Objects.equals(workoutName, other.workoutName);
	}

}
