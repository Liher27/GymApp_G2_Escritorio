package main.backups;

import java.io.IOException;

public class BackupMaker extends AbstractBackupMaker {

	public void doBackup() throws IOException {
		ProcessBuilder processBuilder = new ProcessBuilder(processName, "/c", writeCommand);
		Process process = processBuilder.start();
	}

	public void getBackup() throws IOException {
		ProcessBuilder processBuilder = new ProcessBuilder(processName, "/c", readCommand);
		Process process = processBuilder.start();
	}

}
