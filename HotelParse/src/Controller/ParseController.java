package Controller;

import org.json.simple.parser.ParseException;

import Parser.AgodaParser;
import Parser.BookingDotComParser;
import Parser.DateConverter;
import Parser.ExpediaParser;
import Parser.RoomPrice;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import HotelIdentifier.*;

public class ParseController {
	
	private static String[] hotelList = {"Ninetree","Sejong","Pacific","Manu","Shinshin","Skypark"}; 
	
	public static void main(String args[]) throws MalformedURLException,IOException, ParseException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		int idays = 5;
		int num_days=10;
		ArrayList<RoomPrice> pList = new ArrayList<RoomPrice>();
		
		while (idays<num_days){
			//Setting date
			String checkin=DateConverter.getDate(idays );
			String checkout=DateConverter.getDate(idays+1);
			System.out.print("Check in-"+checkin+";");
			//calling Parsers
			//pList.addAll(AgodaParser.getRooms(new AgodaIdentifier().getSejong(), checkin, checkout));
			//ArrayList<RoomPrice> todayPrice = ExpediaParser.getRooms(new ExpediaIdentifier().getSkypark(), checkin.replace("-","."), checkout.replace("-","."));
			pList.addAll(getAllPriceOfDate(checkin,checkout));
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

	//For code Readability
	private static ArrayList<RoomPrice> getAllPriceOfDate(String checkin, String checkout) throws NoSuchMethodException, SecurityException, MalformedURLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ArrayList<RoomPrice> rpList = new ArrayList<RoomPrice>();
		AgodaIdentifier AI = new AgodaIdentifier();
		ExpediaIdentifier EI = new ExpediaIdentifier();
		BookingDotComIdentifier BI = new BookingDotComIdentifier();
		
		for(String hotel : hotelList)
		{
			//Expedia
			System.out.print(hotel+",");
			Method hotelGetter = getHotelGetter(EI,hotel);
			rpList.addAll(ExpediaParser.getRooms((String) hotelGetter.invoke(EI), checkin.replace("-","."), checkout.replace("-",".")));
			
			//Booking
			//hotelGetter = getHotelGetter(BI,hotel);
			//rpList.addAll(BookingDotComParser.getRoom((String) hotelGetter.invoke(BI), checkin, checkout));
		}
		
		return rpList;
	}
	
	private static Method getHotelGetter(HotelIdentifier hi, String hotelName)
	{
		Method[] methods = hi.getClass().getMethods();
		for (Method m : methods){
		    //System.out.println(m);
		    if (m.getName().equals("get"+hotelName)){
		       return m;
		    }
		}
		return null;
	}
	
}
