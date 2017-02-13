import Exceptions.ShiftingBefore0LevelException;
import Exceptions.WrongSubtitleNumberException;
import Exceptions.WrongTimeFormatException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.regex.Pattern;

/**
 * Created by Monika_ on 2016-11-04.
 */
public class SubRipParser extends Parser {
	long subtitleCounter = 0;
	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
	
	public SubRipParser() {
		super();
	}
	
	public SubRipParser(String filetoParse, String fileToWrite) {
		super(filetoParse, fileToWrite);
	}
	
	@Override
	public void checkIfAllDataAreCorrect(String subtitleRecord) {
		super.checkIfAllDataAreCorrect(subtitleRecord);
		checkCounter(subtitleRecord);
	}
	
	public void checkCounter(String subtitleRecord) {
		subtitleCounter++;
		int counter = Integer.parseInt(getField(subtitleRecord, "NUMBER"));
		if (subtitleCounter != counter) {
			throw new WrongSubtitleNumberException(
				   String.format("Wrong subtitle number, get: %s, expected: %d", counter, subtitleCounter));
		}
	}
	
	@Override
	public String shiftTime(String currentTimeInSpecificRepresentation, int ShiftingtimeInMiliseconds) {
		LocalTime currentTime = getTime(currentTimeInSpecificRepresentation);
		if (currentTime.toNanoOfDay() / 1000000 + ShiftingtimeInMiliseconds < 0) {
			throw new ShiftingBefore0LevelException(
				   String.format("Cannot shift before 00:00, %s", currentTimeInSpecificRepresentation));
		}
		currentTime = currentTime.plus(ShiftingtimeInMiliseconds, ChronoField.MILLI_OF_DAY.getBaseUnit());
		String time = currentTime.format(FORMATTER);
		time = time.replaceAll("\\.", "\\,");
		return time;
	}
	
	@Override
	public int compareTime(String a, String b) {
		
		LocalTime begin = LocalTime.parse(a, FORMATTER);
		LocalTime end = LocalTime.parse(b, FORMATTER);
		return begin.compareTo(end);
	}
	
	public LocalTime getTime(String time) {
		
		try {
			LocalTime currentTime = LocalTime.parse(time, FORMATTER);
			return currentTime;
		} catch (DateTimeParseException e) {
			throw new WrongTimeFormatException(String.format("Bad time format, %s", time));
		}
	}
	
	@Override
	public Pattern correctRecordPattern() {
		return Pattern.compile(
			   "(?<NUMBER>[\\d]+)[\\r][\\n](?<BEGIN>\\d{2}\\:\\d{2}:\\d{2},\\d{3})[\\s]-->[\\s](?<END>\\d{2}\\:\\d{2}:\\d{2},\\d{3})([^\\d]+)");
	}
	
	@Override
	public void setRecordPattern() {
		this.recordPattern = Pattern.compile("[^\\n]*\\n[^\\n]*\\r?\\n(.+[\\r?]\\n)*");
	}
	
	@Override
	public void setTime0Level() {
		this.Time0Level = "00:00:00,000";
	}
	
	@Override
	public void setTimePattern() {
		timePattern = Pattern.compile("\\d{2}\\:\\d{2}:\\d{2},\\d{3}");
	}
	
	public void setSubtitleCounter(long subtitleCounter) {
		this.subtitleCounter = subtitleCounter;
	}
}
