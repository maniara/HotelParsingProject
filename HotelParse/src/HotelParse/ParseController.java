package HotelParse;
import java.util.Calendar;
import java.util.Locale;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class ParseController {
	public static void main(String args[]) throws MalformedURLException,IOException
	{
		int idays = 0;
		int num_days=30;
		ArrayList<RoomPrice> pList = new ArrayList<RoomPrice>();
		
		while (idays<num_days){
			//Setting date
			String checkin=DateConverter.getDate(idays );
			String checkout=DateConverter.getDate(idays+1);
			
			//calling Parsers
			//pList.addAll(AgodaParser.getRooms("nine-tree-hotel-myeong-dong", checkin, checkout));
			pList.addAll(ExpediaParser.getRooms("Seoul-Hotels-Nine-Tree-Hotel", checkin, checkout));
		
			idays++;
			
			//when fast requests have problem use sleep
			try {
			    Thread.sleep(0);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
	
}
