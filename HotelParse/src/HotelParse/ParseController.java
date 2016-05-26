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
		ArrayList<RoomPrice> pList = new ArrayList();
		
		while (idays<num_days){
			//DateConverter temp = new DateConverter();
			String checkin=DateConverter.getDate(idays );
			String checkout=DateConverter.getDate(idays+1);
			//ArrayList<RoomPrice> pList = new AgodaParser().getRooms("", checkin, checkout);
			pList.addAll(AgodaParser.getRooms("nine-tree-hotel-myeong-dong", checkin, checkout));
		
			idays++;
		}
		
		for(RoomPrice rp : pList)
		{
			System.out.println(rp.toString());
		}
	}
	
}
