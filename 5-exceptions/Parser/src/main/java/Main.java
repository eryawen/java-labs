/**
 * Created by Monika_ on 2016-11-06.
 */
public class Main {
	public static void main(String[] args) {
		//MicroDVDParser microDVDParser = new MicroDVDParser("D:/first.sub", "D:/first2.sub");
		//microDVDParser.shiftSubtitles(1200);
		SubRipParser subRipParser = new SubRipParser("D:\\second.srt", "D:\\second2.srt");
		subRipParser.shiftSubtitles(1, 2, 3);
	}
}
