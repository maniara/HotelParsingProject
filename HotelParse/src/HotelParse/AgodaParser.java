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
	public static ArrayList<RoomPrice> getRooms(String hotelName, String checkInDate, String checkOutDate)
			throws MalformedURLException, IOException {

		ArrayList<RoomPrice> roomPriceList = new ArrayList<RoomPrice>();

		String url = "http://www.agoda.com/ko-kr/" + hotelName
				+ "/hotel/seoul-kr.html?pagetypeid=7&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn=" + checkInDate
				+ "&checkout=" + checkOutDate + "&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true";

		//if the return price is not KRW, success is false
		boolean success = false;

		while (success == false) {
			Source source = new Source(new URL(url));
			
			//check the currency
			if (!source.getFirstElementByClass("currency anchor").getContent().toString().equals("KRW"))
				break;
			
			//parsing if the currency is KRW
			else {
				success = true;
				for (Element e1 : source.getAllElements()) {
					if (e1.getAttributeValue("data-selenium") == null)
						continue;

					//each room types are starting here
					if (e1.getAttributeValue("data-selenium").equals("room-item")) {
						
						boolean includedBreakfast = false;
						
						//breakfast include check
						for(Element subE : e1.getAllElements())
						{
							if (subE.getAttributeValue("data-selenium") == null)
								continue;
							if (subE.getAttributeValue("data-selenium").equals("breakfast-included")) {
								includedBreakfast = true;
							}
						}
						
						if(!includedBreakfast)
							continue;
						
						String roomType;
						String price;
						String typeKor;
						String typeEng;

						roomType = e1.getAttributeValue("data-room-name");
						Element priceElement = e1.getFirstElementByClass("sellprice purple");
						price = priceElement.getContent().toString().trim().replace(",", "");

						roomPriceList.add(new RoomPrice("Agoda", hotelName, checkInDate, roomType, roomType, price));
					}
				}
			}
		}

		return roomPriceList;
	}
}
