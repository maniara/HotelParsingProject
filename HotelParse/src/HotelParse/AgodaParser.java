package HotelParse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

public class AgodaParser {
	public static ArrayList<RoomPrice> getRooms(String hotelName, String checkInDate, String checkOutDate) throws MalformedURLException,IOException{
		
		ArrayList<RoomPrice> roomPriceList = new ArrayList<RoomPrice>();
		
		//String url =
		 //"http://www.agoda.com/ko-kr/"+hotelName+"/hotel/seoul-kr.html?asq=r2grbjEASwGFtAW0rdj6nXG4s6hI%2fU6UzLzliGL7s2Qvee45iX9%2by9l4qcWSppbOu7380eYtA6P088u7B52Y5OoGPQGFw2APx1yVzBOrCDrGB49jKpm17msrzrMjaKms%2b0l33YdCWxhkjWx3Ys3Wm%2fLoDDgRc6WQkUOqVOxnO6d35htW7SeK1yBqrgehy5VjMWr9kwuA17ZV8N5ddxLteEqI7gGGHwQu3f5csAilUf%2bsuGhvXoqPvUpEDO0FFSMy&tick=635992835153&pagetypeid=7&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn="+checkInDate+"&checkout="+checkOutDate+"&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true";
		//String url = "http://sofar-val.appspot.com/Login.html";
String url = 
		"http://www.agoda.com/ko-kr/"+hotelName+"/hotel/seoul-kr.html?pagetypeid=7&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn="+checkInDate+"&checkout="+checkOutDate+"&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true";
		
		Source source = new Source(new URL(url));
		for(Element e1 : source.getAllElements())
		{
			if(e1.getAttributeValue("data-selenium") == null)
				continue;
			if(e1.getAttributeValue("data-selenium").equals("room-item"))
			{
				String roomType;
				String price;
				String typeKor;
				String typeEng;
				
				roomType=e1.getAttributeValue("data-room-name");
				//typeKor = roomType.split("(")[0];
				//typeEng = roomType.split("(")[1].replace(")","");
				
				Element roomElement = e1.getFirstElementByClass("room-infos clearfix");

				Element priceElement = e1.getFirstElementByClass("sellprice purple");
				price = priceElement.getContent().toString();
				
				roomPriceList.add(new RoomPrice("Agoda",hotelName,checkInDate,roomType,roomType,price));
			}
		}
		
		return roomPriceList;
	}
}
