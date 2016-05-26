import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import HotelParse.BookingDotComParser;

public class BookingDotComParserTest {
	@Test
	public void getRoomTest() throws MalformedURLException, IOException{
		BookingDotComParser.getRooms("","","");
	}
	

}
