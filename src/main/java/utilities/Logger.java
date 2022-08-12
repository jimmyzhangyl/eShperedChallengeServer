package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	// TODO: log into firebase instead
	private File log;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	
	/**
	 * init a local log.txt file and log into this file
	 */
	public Logger() {
		try {
			this.log = new File("log.txt");
			if (!log.exists())
				log.createNewFile();
			this.fileWriter = new FileWriter(log, true);
			this.bufferedWriter = new BufferedWriter(this.fileWriter);
		} catch (IOException e) {
			System.out.print(e);
		}
	}

	public void addActivity(String activity) {
		try {
			this.bufferedWriter.write(String.format("Activity: %s\n", activity));
		} catch (IOException e) {
			System.out.print(e);
		}

	}

	public void addHeader(String logLocation) {
		// create timestamp
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		try {
			this.bufferedWriter.write(String.format("%s | %s \n", formattedDate, logLocation));
		} catch (IOException e) {
			System.out.print(e);
		}

	}

	public void close() {
		try {
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}