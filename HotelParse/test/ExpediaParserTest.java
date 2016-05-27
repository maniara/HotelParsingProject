import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import HotelParse.ExpediaParser;

public class ExpediaParserTest {
	@Test
	public void getRoomTest() throws MalformedURLException, IOException{
		ExpediaParser.getRooms("","","");
	}
	

}
