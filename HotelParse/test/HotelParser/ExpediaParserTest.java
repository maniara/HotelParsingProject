package HotelParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import HotelParse.ExpediaParser;
import HotelParse.RoomPrice;

public class ExpediaParserTest {
	@Test
	public void getRoomTest() throws MalformedURLException, IOException, ParseException{
		ArrayList<RoomPrice> pList = ExpediaParser.getRooms("Seoul-Hotels-Nine-Tree-Hotel","2016-6-16","2016-6-17");
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
	

}
