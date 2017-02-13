import Exceptions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Monika_ on 2016-11-04.
 */
public abstract class Parser {
	protected Pattern recordPattern;
	//protected Pattern correctRecordPattern;
	protected Pattern timePattern;
	protected ParserIO parserIO;
	protected String Time0Level;
	
	public Parser() {
		this.setRecordPattern();
		// this.setCorrectRecordPattern();
		this.setTime0Level();
		this.setTimePattern();
	}
	
	public Parser(String filetoParse, String fileToWrite) {
		this();
		parserIO = new ParserIO(filetoParse, fileToWrite);
	}
	
	public void shiftSubtitles(int miliseconds) {
		String record = parserIO.readRecord(recordPattern);
		while (record != null) {
			if (checkIfMatchesRegex(record)) {
				checkIfAllDataAreCorrect(record);
				String newRecord = createNewRecord(record, miliseconds);
				parserIO.writeToFile(newRecord);
			} else {
				diagnoseProblemInWrongRecord(record);
				throw new ParsingException(String.format("Bad line format, %s", record));
			}
			record = parserIO.readRecord(recordPattern);
		}
	}
	
	public boolean checkIfMatchesRegex(String record) {
		Matcher m = correctRecordPattern().matcher(record);
		return m.find();
	}
	
	public void checkIfAllDataAreCorrect(String record) {
		if (compareTime(getField(record, "BEGIN"), getField(record, "END")) > 0) {
			throw new BeginGreaterThanEndException(String.format("Begin is greater than end, %s", record));
		}
		Matcher matcher = timePattern.matcher(record);
		matcher.find();
		matcher.find();
		if (matcher.find()) {
			throw new NoRecordSeparator(String.format("No record separator, %s", record));
		}
	}
	
	protected String createNewRecord(String oldRecord, int miliseconds) {
		String begin = getField(oldRecord, "BEGIN");
		String end = getField(oldRecord, "END");
		String newBegin = shiftTime(begin, miliseconds);
		String newEnd = shiftTime(end, miliseconds);
		
		String newRecord = oldRecord.replace(end, newEnd);
		newRecord = newRecord.replace(begin, newBegin);
		
		return newRecord;
	}
	
	public void diagnoseProblemInWrongRecord(String record) {
		Matcher matcher = timePattern.matcher(record);
		if (matcher.find() == false) {
			throw new NoFramesException(String.format("No frames found, %s", record));
		} else if (matcher.find() == false) {
			throw new NoEndFrameException(String.format("No end frame found, %s", record));
		}
	}
	
	public String getField(String record, String groupName) {
		Matcher m = correctRecordPattern().matcher(record);
		m.find();
		String field = m.group(groupName);
		return field;
	}
	
	protected abstract Pattern correctRecordPattern();
	
	public abstract String shiftTime(String currentTimeinSpecificRepresentation,
							   int miliseconds) throws ShiftingBefore0LevelException;
	
	public abstract int compareTime(String a, String b);
	
	public abstract void setRecordPattern();
	
	public abstract void setTimePattern();
	
	public abstract void setTime0Level();
	
	public void shiftSubtitles(int seconds, int miliseconds) {
		shiftSubtitles(seconds * 1000 + miliseconds);
	}
	
	public void shiftSubtitles(int minutes, int seconds, int miliseconds) {
		shiftSubtitles(minutes * 60 * 1000 + seconds * 1000 + miliseconds);
	}
}
