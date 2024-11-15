package main.backups;

public abstract class AbstractBackupMaker {


	protected String processName = "CMD";
	protected String readCommand = "java src/main/backups/BackupReaderExecutable.java";
	protected static String path = "C://trastero//";
	protected String userFileName = path + "User.dat";
	protected static String workoutFileName = path + "Workouts.dat";

}
