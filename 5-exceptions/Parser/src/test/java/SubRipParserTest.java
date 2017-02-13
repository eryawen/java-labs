import Exceptions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Monika_ on 2016-11-05.
 */
public class SubRipParserTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void getTime_TooManyChars_ThrowsWrongTimeFormatException() throws Exception {
		thrown.expect(WrongTimeFormatException.class);
		String time = "11:22:33,4444";
		SubRipParser subRipParser = new SubRipParser();
		subRipParser.getTime(time);
	}
	
	@Test
	public void getTime_MinutesGreaterThan60_ThrowsWrongTimeFormatException() throws Exception {
		thrown.expect(WrongTimeFormatException.class);
		String time = "11:61:33,444";
		SubRipParser subRipParser = new SubRipParser();
		subRipParser.getTime(time);
	}
	
	@Test
	public void checkCounter_WrongNumber_ThrowsWrongSubtitleNumberException() throws Exception {
		thrown.expect(WrongSubtitleNumberException.class);
		SubRipParser subRipParser = new SubRipParser();
		subRipParser.setSubtitleCounter(5);
		String record = "4\r\n00:01:15,817 --> 00:01:19,354\r\nYo también pensaba eso, pero...\r\n¡Sorpresa!\r\n";
		subRipParser.checkCounter(record);
	}
	
	@Test
	public void shiftTime_Before0Level_ThrowsShiftingBefore0LevelException() throws Exception {
		thrown.expect(ShiftingBefore0LevelException.class);
		String time = "00:00:00,326";
		SubRipParser subRipParser = new SubRipParser();
		subRipParser.shiftTime(time, -2000);
	}
	
	@Test
	public void compareTime_ShouldBeGreaterThan0() {
		Parser parser = new SubRipParser();
		assertTrue("Result should be greater than 0", parser.compareTime("00:28:36,632", "00:26:37,397") > 0);
	}
	
	@Test
	public void compareTime_ResultShouldBe0() {
		Parser parser = new SubRipParser();
		assertEquals("Result should be 0", parser.compareTime("00:00:35,348", "00:00:35,348"), 0);
	}
	
	@Test
	public void checkIfmatchesRegex_CorrectData_ShouldReturnTrue() throws Exception {
		SubRipParser subRipParser = new SubRipParser();
		String record = "4\r\n00:01:15,817 --> 00:01:19,354\r\nYo tambin pensaba eso, pero...\r\nSorpresa\n";
		assertTrue(subRipParser.checkIfMatchesRegex(record));
	}
	
	@Test
	public void createNewRecord_timeIsDifferent() throws Exception {
		SubRipParser subRipParser = new SubRipParser();
		String record = "4\r\n00:01:15,817 --> 00:01:19,354\r\nYo tambin pensaba eso, pero...\r\nSorpresa\n";
		String newRecord = subRipParser.createNewRecord(record, 1200);
		assertEquals(newRecord, "4\r\n00:01:17,017 --> 00:01:20,554\r\nYo tambin pensaba eso, pero...\r\nSorpresa\n");
	}
	
	@Test
	public void checkIfAllDataAreCorrect_BeginGreaterThanEnd_ThrowsBeginGreaterThanEndException() throws Exception {
		thrown.expect(BeginGreaterThanEndException.class);
		SubRipParser subRipParser = new SubRipParser();
		String record = "4\r\n00:01:20,817 --> 00:01:19,354\r\nYo tambin pensaba eso, pero...\r\nSorpresa\n";
		subRipParser.checkIfAllDataAreCorrect(record);
	}
	
	@Test
	public void diagnoseProblemWithWrongRecord_NoFramesException_throwsNoEndFrameException() {
		String record = "4\r\n --> \r\nYo tambin pensaba eso, pero...\r\nSorpresa\n";
		thrown.expect(NoFramesException.class);
		SubRipParser subripParser = new SubRipParser();
		subripParser.diagnoseProblemInWrongRecord(record);
	}
	
	@Test
	public void diagnoseProblemWithWrongRecord_NoEndFrame_throwsNoEndFrameException() {
		String record = "4\r\n00:01:20,817 --> \r\nYo tambin pensaba eso, pero...\r\nSorpresa\n";
		thrown.expect(NoEndFrameException.class);
		SubRipParser subripParser = new SubRipParser();
		subripParser.diagnoseProblemInWrongRecord(record);
	}
}
