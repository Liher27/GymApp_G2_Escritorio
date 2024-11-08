package main.manager.pojo;

import java.util.Date;
import java.util.Objects;

public class User {
	private String name = null;
	private String pass = null;
	private String mail = null;
	private String surname = null;
	private Date birthDate = null;
	private int userLevel = 0;
	private boolean trainer = false;

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String password) {
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
	public int hashCode() {
		return Objects.hash(birthDate, mail, name, pass, surname, trainer, userLevel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(mail, other.mail)
				&& Objects.equals(name, other.name) && Objects.equals(pass, other.pass)
				&& Objects.equals(surname, other.surname) && trainer == other.trainer && userLevel == other.userLevel;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", pass=" + pass + ", mail=" + mail + ", surname=" + surname + ", birthDate="
				+ birthDate + ", userLevel=" + userLevel + ", trainer=" + trainer + "]";
	}
}
