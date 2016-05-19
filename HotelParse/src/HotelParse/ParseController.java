package HotelParse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class ParseController {
	public static void main(String args[]) throws MalformedURLException,IOException
	{
		ArrayList<RoomPrice> pList = new AgodaParser().getRooms("", "2016-06-01", "2016-06-02");
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}

}
