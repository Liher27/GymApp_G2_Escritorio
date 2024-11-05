package main.manager.pojo;

import java.util.List;
import java.util.Objects;

public class Workout {

	private int level = 0;
	private String workoutName = null;
	private int exerciseNumber = 0;
	private String video = null;
	private List<Exercise> exercises;
	private int workoutId = 0;
	
	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
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
				+ ", video=" + video + ", id=" + workoutId + ", exercises=" + exercises + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(exerciseNumber, exercises, workoutId, level, video, workoutName);
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
		return exerciseNumber == other.exerciseNumber && Objects.equals(exercises, other.exercises) && workoutId == other.workoutId
				&& level == other.level && Objects.equals(video, other.video)
				&& Objects.equals(workoutName, other.workoutName);
	}

}
