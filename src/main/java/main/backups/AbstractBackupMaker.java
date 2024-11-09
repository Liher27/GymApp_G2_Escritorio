package main.backups;

public abstract class AbstractBackupMaker {

	protected String processName = "CMD";
	protected String readCommand = "java src/main/backups/BackupReaderExecutable.java";
	protected String writeCommand = "java src/main/backups/BackupWriterExecutable.java";
	protected String path = "C://trastero//";
	protected String userFileName = path + "User.dat";
	protected String workoutFileName = path + "Workouts.dat";
	protected String historicFileName = path + "Historic.dat";

}
