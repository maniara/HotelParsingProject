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
		int idays = 1;
		int num_days=30;
		ArrayList<RoomPrice> pList = new ArrayList<RoomPrice>();
		
		while (idays<num_days){
			//Setting date
			String checkin=DateConverter.getDate(idays );
			String checkout=DateConverter.getDate(idays+1);
			System.out.println("Check in-"+checkin+";");
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
			System.out.println(hotel+" Searching... : ");
			
			//Agoda
			Method agodaHotelGetter = getHotelGetter(AI,hotel);
			ArrayList<RoomPrice> agRp = AgodaParser.getRooms(hotel, (String) agodaHotelGetter.invoke(AI), checkin.replace("-","."), checkout.replace("-","."));
			rpList.addAll(agRp);
						
			//Expedia
			Method expediaHotelGetter = getHotelGetter(EI,hotel);
			ArrayList<RoomPrice> epRp = ExpediaParser.getRooms(hotel, (String) expediaHotelGetter.invoke(EI), checkin.replace("-","."), checkout.replace("-",".")); 
			rpList.addAll(epRp);
			
			//Booking
			Method bookingHotelGetter = getHotelGetter(BI,hotel);
			ArrayList<RoomPrice> bkRp = BookingDotComParser.getRoom(hotel, (String) bookingHotelGetter.invoke(BI), checkin, checkout); 
			rpList.addAll(bkRp);
			
			System.out.print(hotel + ": Collected "+agRp.size()+" prices in Agoda, ");
			System.out.print(epRp.size()+" prices in Expedia and ");
			System.out.println(bkRp.size()+" prices in Booking.com in "+ checkin);
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
