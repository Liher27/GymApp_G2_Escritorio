package main.manager.pojo;

import java.util.Objects;

public class Historic {

	private int level = 0;
	private String workoutName = null;
	private int exerciseNumber = 0;
	private String exerciseName = null;
	private int seriesNumber = 0;
	private int restTime = 0;

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

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public int getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(int seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exerciseName, exerciseNumber, level, restTime, seriesNumber, workoutName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historic other = (Historic) obj;
		return Objects.equals(exerciseName, other.exerciseName) && exerciseNumber == other.exerciseNumber
				&& level == other.level && restTime == other.restTime && seriesNumber == other.seriesNumber
				&& Objects.equals(workoutName, other.workoutName);
	}

	@Override
	public String toString() {
		return "Historic [level=" + level + ", workoutName=" + workoutName + ", exerciseNumber=" + exerciseNumber
				+ ", exerciseName=" + exerciseName + ", seriesNumber=" + seriesNumber + ", restTime=" + restTime + "]";
	}

}
