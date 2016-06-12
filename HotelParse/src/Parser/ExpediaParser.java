package Parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import RequestHandler.SeleniumRequester;
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
	public final static int MAX_PRICE = 10000000;
	
	
	public static ArrayList<RoomPrice> getRooms(String hotelName, String checkInDate, String checkOutDate)
			throws MalformedURLException, IOException {
		//System.out.println("Get room information : "+checkInDate);
		ExpediaParser.hotelName = hotelName;
		ExpediaParser.checkInDate = checkInDate;
		ExpediaParser.checkOutDate = checkOutDate;

		//https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016.6.12&chkout=2016.6.13&rm1=a1#chkin=2016.06.11&chkout=2016.06.12&adults=1&children=0&swpToggleOn=false&ts=1465293392808
		String url = "https://www.expedia.co.kr/"+hotelName+".Hotel-Information?chkin="+checkInDate+"&chkout="+checkOutDate+"&rm1=a2";
		System.out.println(url);
		//"https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016.06.13&chkout=2016.06.14&rm1=a2&hwrqCacheKey=b8bbe2f0-0d2f-41ec-9db8-ca025f4530f0HWRQ1464857938255&c=30966cb8-c3d9-4276-bc5c-5a3311cfe3d7&";
		//"https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016-6-6&chkout=2016-6-7&rm1=a2#rooms-and-rates"
		//"https://www.expedia.co.kr/Seoul-Hotels-Sejong-Hotel.h7239.Hotel-Information?chkin=2016.06.11&chkout=2016.06.12&rm1=a2&hwrqCacheKey=79659934-84cc-440f-9db2-a82077ce3c33HWRQ1465480890682&c=c6afbdfd-95ec-4fab-9c10-fb66d0189130&"
		//"https://www.expedia.co.kr/Seoul-Hotels-Sejong-Hotel.h7239.Hotel-Information?chkin=2016.6.11&chkout=2016.6.12&rm1=a2"
		String htmlString = SeleniumRequester.getResponse(url);
		
		//if Null data comes flag became false
		while (successFlag == false) {
			
			Source source = new Source(htmlString);
			
			//Element e = source.getFirstElementByClass("room room-above-fold");
			//System.out.println(e.toString());
			/*for(Element e :source.getAllElementsByClass("room room-above-fold")){
				System.out.println(e.getFirstElementByClass("room-basic-info").toString());
			successFlag = true;
			}*/
			
			
			//System.out.println("CHECK");
			//No room on the dates
			if(source.getFirstElementByClass("room-basic-info") == null){
				System.out.println("No room-basic-info tag");
				return roomPriceList;
			}
			
			//No type on the dates
			if(source.getFirstElementByClass("room-basic-info").getAllElements("h3") == null){
				System.out.println("No H3 tag in room-basic-info");
				continue;
			}
			
			//No type on the dates
			if(source.getAllElementsByClass("room room-above-fold first-room-featured").size()==0){
				System.out.println("No room room-above-fold tag");
				continue;
			}
			
			//No vacant
			if(source.getAllElementsByClass("room-price ").size() == 0){
				//System.out.println("1");
				break;
			}
			if(source.getAllElementsByClass("room-basic-info").size() == 0){
				//System.out.println("2");
				break;
			}
			if(source.getAllElementsByClass("room-basic-info").size() == 0){
				//System.out.println("3");
				break;}
			
			/*for (Element e1 : source.getAllElementsByClass("room room-above-fold first-room-featured"))  //firstRoomType
			{
				//System.out.println("first");
				getRoomTypeInformation(e1);
			}*/

			//Loop on each type
			//System.out.println(source.getAllElementsByClass("room room-above-fold").size());
			for (Element e1 : source.getAllElementsByClass("room room-above-fold"))  
			{
				//System.out.println("second");
				getRoomTypeInformation(e1);
			}
		}
		successFlag = false;
		return roomPriceList;
	}

	private static void getRoomTypeInformation(Element e1) {
		RoomPrice rp = new RoomPrice("Expedia",ExpediaParser.hotelName,ExpediaParser.checkInDate);
		int minPrice = MAX_PRICE;
		String type = "";
		
		for(Element typeElement : e1.getFirstElementByClass("room-basic-info").getAllElements("h3")){ //get room type name
			type = typeElement.getContent().toString();
			System.out.println("Type:"+type+"in H3 tag");
			if(type != null){
				//System.out.println("Flag Changed");
				successFlag = true;
				rp.setRoomTypeKor(type);
			}
		}
		
		// In HTML template 2
		if (type.equals("")) {
			Element typeElement = e1.getFirstElementByClass("room-basic-info").getFirstElement("button")
					.getFirstElement("span");
			type = typeElement.getContent().toString();
			System.out.println("Type:"+type+"in button");
			if (type == null) {
				successFlag = false;
				return;
			} else {
				// System.out.println("Flag Changed");
				successFlag = true;
				rp.setRoomTypeKor(type);
			}
		}

		for(Element optionElement : e1.getAllElementsByClass("rate-plan rate-plan-first "))
		{
			if(isForFreeCancle(optionElement) && !isForBreakfast(optionElement))
			{
				int price = getPrice(optionElement);
				//System.out.println("Target!");
				if(minPrice > price){
					minPrice = price;
				}
			}
		}
		
		for(Element optionElement : e1.getAllElementsByClass("rate-plan rate-plan-first"))
		{
			if(isForFreeCancle(optionElement) && !isForBreakfast(optionElement))
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
			if(isForFreeCancle(optionElement) && !isForBreakfast(optionElement))
			{
				int price = getPrice(optionElement);
				//System.out.println("Target!");
				if(minPrice > price){
					minPrice = price;
				}
			}
		}
		
		//maximum price can be printed
		if(minPrice != MAX_PRICE){
			rp.setPriceKRW(minPrice+"");
			if(rp.getRoomTypeKor() != null)
				roomPriceList.add(rp);
			//System.out.println(rp.toString());
		}
	}

	private static int getPrice(Element optionElement) {
		if(optionElement.getFirstElementByClass("room-price ") == null)
			return MAX_PRICE;
		String ret = optionElement.getFirstElementByClass("room-price ").getContent().toString();
		if(ret == null)
			ret = "";
				
		return Integer.parseInt(ret.replace(",", "").replace("₩", "").trim());
	}

	private static boolean isForBreakfast(Element optionElement) {
		boolean retVal = false;
		if(optionElement.getAllElementsByClass("room-amenity free-breakfast").size() != 0)
			retVal = true;
		System.out.println("BK included:"+retVal);
		return retVal;
	}

	private static boolean isForFreeCancle(Element optionElement) {
		boolean retVal = false;
		boolean opt1 = false;
		boolean opt2 = true;
		
		//refundable
		if(optionElement.getAllElementsByClass("free-cancellation-short free-text").size() != 0)
			opt1 = true;
		
		// In HTML template 2
		for(Element e : optionElement.getAllElements())
		{
			//no refundable
			if(e.getAttributeValue("data-track") != null && e.getAttributeValue("data-track").equals("HOT.HIS.RoomsAndRates.NonRefundable"))
				opt2 = false;
		}
		
		if(opt1 == true  || opt2 == true)
			retVal = true;
		
		System.out.println("Free cancle:"+retVal);
		return retVal;
	}
}
