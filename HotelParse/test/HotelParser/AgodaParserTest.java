package HotelParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.Test;

import HotelParse.AgodaParser;
import HotelParse.ExpediaParser;
import HotelParse.RoomPrice;

public class AgodaParserTest {
	@Test
	public void getRoomTest() throws MalformedURLException, IOException{
		ArrayList<RoomPrice> pList = AgodaParser.getRooms("nine-tree-hotel-myeong-dong","2016-6-25","2016-6-26");
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
}
