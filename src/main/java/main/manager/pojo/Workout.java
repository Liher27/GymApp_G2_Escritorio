package main.manager.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Workout implements Serializable {

	private static final long serialVersionUID = 6334434094001570294L;

	private String workoutUID = null;
	private String workoutId = null;
	private int level = 0;
	private String workoutName = null;
	private int exerciseNumber = 0;
	private String video = null;
	private List<Exercise> exercises;

	public String getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(String workoutId) {
		this.workoutId = workoutId;
	}

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
	public int hashCode() {
		return Objects.hash(exerciseNumber, exercises, level, video, workoutId, workoutName, workoutUID);
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
				&& Objects.equals(workoutId, other.workoutId) && Objects.equals(workoutName, other.workoutName)
				&& Objects.equals(workoutUID, other.workoutUID);
	}

	@Override
	public String toString() {
		return "Workout [workoutUID=" + workoutUID + ", level=" + level + ", workoutName=" + workoutName
				+ ", exerciseNumber=" + exerciseNumber + ", video=" + video + ", exercises=" + exercises
				+ ", workoutId=" + workoutId + "]";
	}

}
