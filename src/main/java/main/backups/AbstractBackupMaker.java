package main.backups;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class AbstractBackupMaker {

	protected String processName = "CMD";
	protected String readCommand = "java src/main/backups/BackupReaderExecutable.java";
	protected String writeCommand = "java src/main/backups/BackupWriterExecutable.java";
	protected String path = "C://trastero//";
	protected String userFileName = path + "User.dat";
	protected String workoutFileName = path + "Workouts.dat";
	protected String historicFileName = path + "Historic.dat";

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
