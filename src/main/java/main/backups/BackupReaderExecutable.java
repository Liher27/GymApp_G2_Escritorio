package main.backups;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import main.manager.StatusSingleton;
import main.manager.pojo.User;
import main.manager.pojo.Workout;

public class BackupReaderExecutable extends AbstractBackupMaker {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		BackupReaderExecutable backupReaderExecutable = new BackupReaderExecutable();
		backupReaderExecutable.readDocuments();
	}

	private void readDocuments() throws ClassNotFoundException, IOException {
		setUpWorkouts();
		setUpUser();
	}

	private void setUpUser() throws ClassNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(userFileName));
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		User user = null;
		int index = 0;
		while ((index = fileInputStream.read()) != -1) {
			System.out.print((char) index);
		}
		fileInputStream.close();
		while (fileInputStream.getChannel().position() < fileInputStream.getChannel().size()) {
			user = (User) objectInputStream.readObject();
		}
		objectInputStream.close();
		StatusSingleton.getInstance().setUser(user);
	}

	private void setUpWorkouts() throws ClassNotFoundException, IOException {
		ArrayList<Workout> workouts = new ArrayList<Workout>();
		FileInputStream fileInputStream = new FileInputStream(new File(workoutFileName));
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		Workout workout = null;
		int index = 0;
		while ((index = fileInputStream.read()) != -1) {
			System.out.print((char) index);
		}
		fileInputStream.close();
		while (fileInputStream.getChannel().position() < fileInputStream.getChannel().size()) {
			workout = (Workout) objectInputStream.readObject();
			workouts.add(workout);
		}
		objectInputStream.close();
		System.out.println(workouts.toString());
		StatusSingleton.getInstance().setBackupedWorkouts(workouts);
	}
}