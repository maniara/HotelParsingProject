package HotelParser;

import java.util.ArrayList;

import org.junit.Test;

import HotelIdentifier.BookingDotComIdentifier;
import Parser.BookingDotComParser;
import Parser.RoomPrice;

public class BookingDotComTest {
	@Test
	public void getRoom()
	{
		//ArrayList<RoomPrice> list = BookingDotComParser.getRoom("loisir-seoul-myeongdong", "2016-06-13", "2016-06-14");
		ArrayList<RoomPrice> list = BookingDotComParser.getRoom("Manu",new BookingDotComIdentifier().getManu(), "2016-07-13", "2016-07-14");
		for(RoomPrice rp : list)
		{
			System.out.println(rp.toString());
		}
		
	}

}
