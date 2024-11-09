package main.manager.pojo;

import java.io.Serializable;
import java.util.Objects;

public class Exercise implements Serializable {

	private static final long serialVersionUID = -8609530128554102709L;
	
	private String exerciseName = null;
	private int seriesNumber = 0;
	private String image = null;
	private int restTime = 0;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exerciseName, image, restTime, seriesNumber);
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
		return Objects.equals(exerciseName, other.exerciseName) && Objects.equals(image, other.image)
				&& restTime == other.restTime && seriesNumber == other.seriesNumber;
	}

	@Override
	public String toString() {
		return "Exercise [exerciseName=" + exerciseName + ", seriesNumber=" + seriesNumber + ", image=" + image
				+ ", restTime=" + restTime + "]";
	}

}
