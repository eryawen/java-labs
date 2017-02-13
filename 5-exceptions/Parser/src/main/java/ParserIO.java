import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Monika_ on 2016-11-05.
 */
public class ParserIO {
	protected Scanner scanner;
	private OutputStream out;
	
	public ParserIO(String fileToParse, String fileToWrite) {
		
		this.prepareFileToParse(fileToParse);
		this.createFileToWrite(fileToWrite);
	}
	
	protected void prepareFileToParse(String fileToParse) {
		try {
			scanner = new Scanner(new File(fileToParse), "ISO-8859-1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void createFileToWrite(String fileToWrite) {
		try {
			out = Files.newOutputStream(Paths.get(fileToWrite));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readRecord(Pattern recordPattern) {
		return scanner.findWithinHorizon(recordPattern, 0);
	}
	
	public void writeToFile(String newRecord) {
		try {
			out.write(newRecord.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
