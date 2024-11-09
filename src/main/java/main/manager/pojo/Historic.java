package main.manager.pojo;

import java.util.Date;
import java.util.Objects;

public class Historic extends Workout {

	private int providedTime = 0;
	private int totalTime = 0;
	private Date finishDate = null;
	private String exercisePercent = null;

	public int getProvidedTime() {
		return providedTime;
	}

	public void setProvidedTime(int providedTime) {
		this.providedTime = providedTime;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getExercisePercent() {
		return exercisePercent;
	}

	public void setExercisePercent(String exercisePercent) {
		this.exercisePercent = exercisePercent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(exercisePercent, finishDate, providedTime, totalTime);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historic other = (Historic) obj;
		return Objects.equals(exercisePercent, other.exercisePercent) && Objects.equals(finishDate, other.finishDate)
				&& providedTime == other.providedTime && totalTime == other.totalTime;
	}

	@Override
	public String toString() {
		return "Historic [providedTime=" + providedTime + ", totalTime=" + totalTime + ", finishDate=" + finishDate
				+ ", exercisePercent=" + exercisePercent + ", toString()=" + super.toString() + "]";
	}

}
