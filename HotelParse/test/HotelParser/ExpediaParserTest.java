package HotelParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import HotesIdentifier.ExpediaIdentifier;
import Parser.ExpediaParser;
import Parser.RoomPrice;

public class ExpediaParserTest {
	@Test
	public void getRoomTest() throws MalformedURLException, IOException, ParseException{
		ArrayList<RoomPrice> pList = ExpediaParser.getRooms(new ExpediaIdentifier().getSejong(),"2016.6.12","2016.6.13");
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
	

}
