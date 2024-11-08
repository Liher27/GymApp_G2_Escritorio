package main.backups;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class AbstractBackup {

	public abstract class AbstractFileManager {
		protected String path = "C://trastero//";
		protected String userFileName = path + "User.dat";
		protected String workoutFileName = path + "Workouts.dat";
		protected String exerciseFileName = path + "Exercises.dat";
		protected String processName = "CMD";
		protected String command = "java src/ejercicio1/EjemploLectura.java";

		public class AppendableObjectOutputStream extends ObjectOutputStream {
			public AppendableObjectOutputStream(FileOutputStream fileOutputStream) throws IOException {
				super(fileOutputStream);
			}

			@Override
			protected void writeStreamHeader() throws IOException {
				reset();
			}
		}

		protected boolean fileIsEmpty(String file) {
			return new File(file).length() == 0;
		}
	}
}
