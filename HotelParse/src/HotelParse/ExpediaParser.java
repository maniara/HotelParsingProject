package HotelParse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

public class ExpediaParser {
	
	private static boolean successFlag = false;
	private static ArrayList<RoomPrice> roomPriceList = new ArrayList<RoomPrice>();
	public static String hotelName;
	public static String checkInDate;
	public static String checkOutDate;
	
	
	public static ArrayList<RoomPrice> getRooms(String hotelName, String checkInDate, String checkOutDate)
			throws MalformedURLException, IOException, ParseException {
		//System.out.println("Get room information : "+checkInDate);
		ExpediaParser.hotelName = hotelName;
		ExpediaParser.checkInDate = checkInDate;
		ExpediaParser.checkOutDate = checkOutDate;

		//String url = "https://www.expedia.co.kr/"+hotelName+".h6084370.Hotel-Information?chkin="+checkInDate+"&chkout="+checkOutDate+"&rm1=a2#rooms-and-rates";
		//System.out.println(url);
		String url = "https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016.06.13&chkout=2016.06.14&rm1=a2&hwrqCacheKey=b8bbe2f0-0d2f-41ec-9db8-ca025f4530f0HWRQ1464857938255&c=30966cb8-c3d9-4276-bc5c-5a3311cfe3d7&";
		
		
		//if Null data comes flag became false
		while (successFlag == false) {
			
			
			Source source = new Source(new URL(url));
			
			for(Element e : source.getAllElements("script"))
			{
				if(e.toString().contains("var utag_data = ")){
					getJson(e.getContent().toString().replace("var utag_data = ", "").replace(";", ""));
				}
			}
		
		}
		successFlag = false;
		return roomPriceList;
	}

	private static void getJson(String jsonString) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject object = (JSONObject) jsonParser.parse(jsonString);
		System.out.println(object);
	}

/*	private static void getRoomTypeInformation(Element e1) {
		RoomPrice rp = new RoomPrice("Expedia",ExpediaParser.hotelName,ExpediaParser.checkInDate);
		int minPrice = 10000000;
		
		for(Element typeElement : e1.getFirstElementByClass("room-basic-info").getAllElements("h3")){ //get room type name
			String type = typeElement.getContent().toString();
			if(type == null){
				successFlag = false;
				return;
			}
			else{
				successFlag = true;
				rp.setRoomTypeKor(type);
			}
		}
		
		for(Element optionElement : e1.getAllElementsByClass("rate-plan rate-plan-first "))
		{
			if(isForFreeCancle(optionElement) && isForBreakfast(optionElement))
			{
				int price = getPrice(optionElement);
				//System.out.println("Target!");
				if(minPrice > price){
					minPrice = price;
				}
			}
		}
		
		for(Element optionElement : e1.getAllElementsByClass("rate-plan "))
		{
			if(isForFreeCancle(optionElement) && isForBreakfast(optionElement))
			{
				int price = getPrice(optionElement);
				//System.out.println("Target!");
				if(minPrice > price){
					minPrice = price;
				}
			}
		}
		
		//maximum price can be printed
		if(minPrice != 10000000){
			rp.setPriceKRW(minPrice+"");
			if(rp.getRoomTypeKor() != null)
				roomPriceList.add(rp);
			//System.out.println(rp.toString());
		}
	}

	private static int getPrice(Element optionElement) {
		if(optionElement.getFirstElementByClass(" room-price ") == null)
			return 10000000;
		String ret = optionElement.getFirstElementByClass(" room-price ").getContent().toString();
		if(ret == null)
			ret = "";
				
		return Integer.parseInt(ret.replace(",", "").replace("₩", ""));
	}

	private static boolean isForBreakfast(Element optionElement) {
		boolean retVal = false;
		if(optionElement.getAllElementsByClass("room-amenity free-breakfast").size() != 0)
			retVal = true;
		return retVal;
	}

	private static boolean isForFreeCancle(Element optionElement) {
		boolean retVal = false;
		if(optionElement.getAllElementsByClass("free-cancellation-short free-text").size() != 0)
			retVal = true;
		return retVal;
	}*/
}
