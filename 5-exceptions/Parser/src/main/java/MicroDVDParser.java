import Exceptions.ShiftingBefore0LevelException;
import Exceptions.WrongTimeFormatException;

import java.util.regex.Pattern;

/**
 * Created by Monika_ on 2016-11-04.
 */
public class MicroDVDParser extends Parser {
	int frameRate = 24;
	int differenceInFrames;
	
	public MicroDVDParser() {
		super();
	}
	
	public MicroDVDParser(int frameRate) {
		this();
		this.frameRate = frameRate;
	}
	
	public MicroDVDParser(String filetoParse, String fileToWrite) {
		super(filetoParse, fileToWrite);
	}
	
	public MicroDVDParser(String filetoParse, String fileToWrite, int frameRate) {
		this(filetoParse, fileToWrite);
		this.frameRate = frameRate;
	}
	
	@Override
	public void shiftSubtitles(int miliseconds) {
		countAndSetDifferenceInFrames(miliseconds);
		super.shiftSubtitles(miliseconds);
	}
	
	@Override
	public String shiftTime(String currentTimeInSpecificRepresentation, int ShiftingtimeInMiliseconds) {
		Integer i = Integer.parseInt(currentTimeInSpecificRepresentation) + differenceInFrames;
		String newTime = String.valueOf(i);
		if (compareTime(newTime, Time0Level) < 0) {
			throw new ShiftingBefore0LevelException(
				   String.format("Cannot shift before movie begin, %s", currentTimeInSpecificRepresentation));
		}
		return newTime;
	}
	
	@Override
	public int compareTime(String a, String b) {
		try {
			Integer begin = Integer.parseInt(a);
			Integer end = Integer.parseInt(b);
			return begin.compareTo(end);
		} catch (NumberFormatException e) {
			throw new WrongTimeFormatException(String.format("Wrong time format, %s, %s", a, b));
		}
	}
	
	public void countAndSetDifferenceInFrames(int timeInMiliseconds) {
		differenceInFrames = timeInMiliseconds * frameRate / 1000;
	}
	
	@Override
	public Pattern correctRecordPattern() {
		return Pattern.compile("\\{(?<BEGIN>\\d+)\\}\\{(?<END>\\d+)\\}([^\\{\\}+])");
	}
	
	@Override
	public void setRecordPattern() {
		this.recordPattern = Pattern.compile("[^\\n]*\\n");
	}
	
	@Override
	public void setTime0Level() {
		this.Time0Level = "0";
	}
	
	@Override
	public void setTimePattern() {
		timePattern = Pattern.compile("\\{(\\d+)\\}");
	}
	
	public int getDifferenceInFrames() {
		return differenceInFrames;
	}
}
