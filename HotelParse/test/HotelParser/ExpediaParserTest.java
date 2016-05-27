package HotelParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.Test;

import HotelParse.ExpediaParser;
import HotelParse.RoomPrice;

public class ExpediaParserTest {
	@Test
	public void getRoomTest() throws MalformedURLException, IOException{
		ArrayList<RoomPrice> pList = ExpediaParser.getRooms("Seoul-Hotels-Nine-Tree-Hotel","2016-6-28","2016-6-29");
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
	

}
