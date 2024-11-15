package main.backups;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class BackupReaderExecutable extends AbstractBackupMaker {

	public static void main(String[] args) {

		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(workoutFileName)));

			System.out.println(objectInputStream.readObject());

			objectInputStream.close();
		} catch (Exception e) {

		}

	}

}