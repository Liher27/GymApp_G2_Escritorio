package main.manager.pojo;

import java.util.Date;
import java.util.Objects;

public class User {
	private String name = null;
	private String password = null;
	private String mail = null;
	private String surname = null;
	private Date birthDate = null;
	private boolean isTrainer = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return isTrainer;
	}

	public void setTrainer(boolean isTrainer) {
		this.isTrainer = isTrainer;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", mail=" + mail + ", surname=" + surname
				+ ", birthDate=" + birthDate + ", isTrainer=" + isTrainer + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, isTrainer, mail, name, password, surname);
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
		return Objects.equals(birthDate, other.birthDate) && isTrainer == other.isTrainer
				&& Objects.equals(mail, other.mail) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}
	
	
}
