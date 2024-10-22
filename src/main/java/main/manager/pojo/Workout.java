package main.manager.pojo;

import java.util.Objects;

public class Workout extends Exercise{

	private int workoutLvl = 0;
	private String workoutName = null;
	private int exerciseNumber = 0;
	private String videoURL = null;

	@Override
	public String toString() {
		return "Workout [workoutLvl=" + workoutLvl + ", workoutName=" + workoutName + ", exerciseNumber="
				+ exerciseNumber + ", videoURL=" + videoURL + "]";
	}

	public int getWorkoutLvl() {
		return workoutLvl;
	}

	public void setWorkoutLvl(int workoutLvl) {
		this.workoutLvl = workoutLvl;
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

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exerciseNumber, videoURL, workoutLvl, workoutName);
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
		return exerciseNumber == other.exerciseNumber && Objects.equals(videoURL, other.videoURL)
				&& workoutLvl == other.workoutLvl && Objects.equals(workoutName, other.workoutName);
	}

}
