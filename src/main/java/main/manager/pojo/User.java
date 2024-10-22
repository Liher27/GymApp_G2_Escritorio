package main.manager.pojo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
	private String name = null;
	private String pass = null;
	private String mail = null;
	private String surname = null;
	private Date birthDate = null;
	private boolean trainer = false;
	private List<Workout> workouts = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isTrainer() {
		return trainer;
	}

	public void setTrainer(boolean isTrainer) {
		this.trainer = isTrainer;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", pass=" + pass + ", mail=" + mail + ", surname=" + surname + ", birthDate="
				+ birthDate + ", trainer=" + trainer + ", workouts=" + workouts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(birthDate, mail, name, pass, surname, trainer, workouts);
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
		User other = (User) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(mail, other.mail)
				&& Objects.equals(name, other.name) && Objects.equals(pass, other.pass)
				&& Objects.equals(surname, other.surname) && trainer == other.trainer
				&& Objects.equals(workouts, other.workouts);
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}

}
