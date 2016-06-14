package HotelParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.Test;

import HotelIdentifier.AgodaIdentifier;
import Parser.AgodaParser;
import Parser.ExpediaParser;
import Parser.RoomPrice;

public class AgodaParserTest {
	@Test
	public void getRoomTest() throws MalformedURLException, IOException{
		ArrayList<RoomPrice> pList = AgodaParser.getRooms("Manu", new AgodaIdentifier().getNinetree(),"2016-7-14","2016-7-15");
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
}
