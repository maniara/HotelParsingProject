package Parser;
import java.util.Calendar;
import java.util.Locale;

import org.json.simple.parser.ParseException;

import HotesIdentifier.AgodaIdentifier;
import HotesIdentifier.ExpediaIdentifier;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class ParseController {
	public static void main(String args[]) throws MalformedURLException,IOException, ParseException
	{
		int idays = 0;
		int num_days=30;
		ArrayList<RoomPrice> pList = new ArrayList<RoomPrice>();
		
		while (idays<num_days){
			//Setting date
			String checkin=DateConverter.getDate(idays );
			String checkout=DateConverter.getDate(idays+1);
			System.out.println("Now reading : "+checkin);
			//calling Parsers
			//ArrayList<RoomPrice> todayPrice = AgodaParser.getRooms(new AgodaIdentifier().getSejong(), checkin, checkout);
			ArrayList<RoomPrice> todayPrice = ExpediaParser.getRooms(new ExpediaIdentifier().getSkypark(), checkin.replace("-","."), checkout.replace("-","."));
			pList.addAll(todayPrice);
			
			
			for(RoomPrice rp : todayPrice)
			{
				System.out.println(rp.toString());
			}
		
			idays++;
			
			//when fast requests have problem use sleep
			try {
			    Thread.sleep(0);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		
		System.out.println("------------");
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
	
}
