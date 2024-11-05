package main.manager.pojo;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Exercise {

	private String exerciseName = null;
	private int seriesNumber = 0;
	private String exerciseImage = null;
	private int rest = 0;
	private int execiseId = 0;

	@Override
	public String toString() {
		return "Exercise [exerciseName=" + exerciseName + ", seriesNumber=" + seriesNumber + ", exerciseImage="
				+ exerciseImage + ", rest=" + rest + ", execiseId=" + execiseId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(exerciseImage, exerciseName, execiseId, rest, seriesNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		return Objects.equals(exerciseImage, other.exerciseImage) && Objects.equals(exerciseName, other.exerciseName)
				&& execiseId == other.execiseId && rest == other.rest && seriesNumber == other.seriesNumber;
	}

	public int getExeciseId() {
		return execiseId;
	}

	public void setExeciseId(int execiseId) {
		this.execiseId = execiseId;
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

	public String getExerciseImage() {
		return exerciseImage;
	}

	public void setExerciseImage(String string) {
		this.exerciseImage = string;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

}
