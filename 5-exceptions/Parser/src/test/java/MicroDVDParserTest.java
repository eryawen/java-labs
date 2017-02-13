import Exceptions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Monika_ on 2016-11-05.
 */
public class MicroDVDParserTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void getField_BEGIN() {
		MicroDVDParser microDVDParser = new MicroDVDParser();
		String begin = microDVDParser.getField("{166}{304}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.",
									    "BEGIN");
		assertEquals(begin, "166");
	}
	
	@Test
	public void checkIfmatchesRegex_ShouldReturnFalse() throws Exception {
		MicroDVDParser microDVDParser = new MicroDVDParser();
		String record = "{166}{304Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.";
		assertFalse(microDVDParser.checkIfMatchesRegex(record));
	}
	
	@Test
	public void checkIfmatchesRegex_ShouldReturnTrue() throws Exception {
		MicroDVDParser microDVDParser = new MicroDVDParser();
		String record = "{166}{304}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.";
		assertTrue("{166}{304}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo. should match regex",
				 microDVDParser.checkIfMatchesRegex(record));
	}
	
	@Test
	public void createNewRecord_TimeIsDifferent() throws Exception {
		MicroDVDParser microDVDParser = new MicroDVDParser();
		microDVDParser.countAndSetDifferenceInFrames(1200);
		String newRecord = microDVDParser.createNewRecord(
			   "{166}{304}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.", 1200);
		assertEquals(newRecord, "{194}{332}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.");
	}
	
	@Test
	public void diagnoseProblemWithWrongRecord_NoFramesException_throwsNoEndFrameException() {
		String record = "Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.\r\n";
		thrown.expect(NoFramesException.class);
		MicroDVDParser microDVDParser = new MicroDVDParser();
		microDVDParser.diagnoseProblemInWrongRecord(record);
	}
	
	@Test
	public void diagnoseProblemWithWrongRecord_NoEndFrame_throwsNoEndFrameException() {
		String record = "{354}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.\r\n";
		thrown.expect(NoEndFrameException.class);
		MicroDVDParser microDVDParser = new MicroDVDParser();
		microDVDParser.diagnoseProblemInWrongRecord(record);
	}
	
	@Test
	public void checkIfAllDataAreCorrect_Begin354End304_throwsBeginGreaterThanEndException() {
		String record = "{354}{304}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo.\r\n";
		thrown.expect(BeginGreaterThanEndException.class);
		MicroDVDParser microDVDParser = new MicroDVDParser();
		microDVDParser.checkIfAllDataAreCorrect(record);
	}
	
	@Test
	public void shift1200miliseconds_timeIs194() {
		MicroDVDParser microDVDParser = new MicroDVDParser();
		microDVDParser.countAndSetDifferenceInFrames(1200);
		assertEquals(microDVDParser.shiftTime("166", 1200), "194");
	}
	
	@Test
	public void shift_Minus21200miliseconds_throwShiftingBefore0LevelException() {
		MicroDVDParser microDVDParser = new MicroDVDParser();
		microDVDParser.countAndSetDifferenceInFrames(-21200);
		thrown.expect(ShiftingBefore0LevelException.class);
		microDVDParser.shiftTime("166", -21200);
	}
	
	@Test
	public void compareTime_123and65_ShouldBeGreaterThan0() {
		Parser parser = new MicroDVDParser();
		assertTrue(parser.compareTime("123", "65") > 0);
	}
	
	@Test
	public void compareTime_23and06565_ShouldBeLowerThan0() {
		Parser parser = new MicroDVDParser();
		assertTrue(parser.compareTime("23", "06565") < 0);
	}
	
	@Test
	public void compareTime_4and9_ShouldEquals0() {
		Parser parser = new MicroDVDParser();
		assertTrue(parser.compareTime("4", "4") == 0);
	}
	
	@Test
	public void countAndSetDifferenceInFrames_500MilisecondsDefaultFrameRate_differenceInFramesShouldBe12() {
		MicroDVDParser parser = new MicroDVDParser();
		parser.countAndSetDifferenceInFrames(500);
		assertEquals(parser.getDifferenceInFrames(), 12);
	}
	
	@Test
	public void checkIfAllDataAreCorrect_TwoRecordsInOneLine_throwsNoRecordSeparator() {
		MicroDVDParser microDVDParser = new MicroDVDParser();
		thrown.expect(NoRecordSeparator.class);
		String record = "{166}{304}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo. {194}{332}Nihče jih ne bo opazil,|dokler boš oblečena v brisačo";
		microDVDParser.checkIfAllDataAreCorrect(record);
	}
}
