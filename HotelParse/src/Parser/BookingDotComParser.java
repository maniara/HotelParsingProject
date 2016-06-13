package Parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import RequestHandler.SeleniumRequester;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;

public class BookingDotComParser {
	public static ArrayList<RoomPrice> getRoom(String hotelName, String checkInDate, String checkOutDate) {
		ArrayList<RoomPrice> retList = new ArrayList<RoomPrice>();
		ArrayList<String> roomTypeList = new ArrayList<String>();
		ArrayList<String> priceList = new ArrayList<String>();
		ArrayList<Boolean> freeCancle = new ArrayList<Boolean>();
		
		
		//http://www.booking.com/hotel/kr/loisir-seoul-myeongdong.ko.html?dcid=1;checkin=2016-06-12;checkout=2016-06-13;
		//dest_type=district;dist=0;group_adults=2;room1=A%2CA;sb_price_type=total;type=total;ucfs=1&
		
		//http://www.booking.com/hotel/kr/loisir-seoul-myeongdong.ko.html?dcid=1;checkin=2016-06-13;checkout=2016-07-20;dest_type=city;dist=0;group_adults=2;hpos=1;room1=A%2CA;sb_price_type=total;type=total;ucfs=1&

		String url = "http://www.booking.com/hotel/kr/" + hotelName + ".ko.html?dcid=1;checkin=" + checkInDate
				+ ";checkout=" + checkOutDate
				+ ";dest_type=city;dist=0;group_adults=2;hpos=1;room1=A%2CA;sb_price_type=total;type=total;ucfs=1&";
		//System.out.println(url);
		String webpage = SeleniumRequester.getResponse(url);

		Source source = new Source(webpage);

		for (Element e : source.getAllElementsByClass("ratepolicy")) {
			if(e.getAttributeValue("data-room-name") != null){
				if(e.toString().contains("환불불가"))
					freeCancle.add(false);
				else
					freeCancle.add(true);
				roomTypeList.add(e.getAttributeValue("data-room-name"));
			}
		}

		for(Element e: source.getAllElements("strong"))
		{
			if(e.getAttributeValue("data-price-without-addons") != null)
				priceList.add(e.getContent().toString().replaceAll("[^\\d.]", ""));
		}
		
		/*for (Element e : source.getAllElementsByClass("roomDefaultUse run_hp_crrt")) {
			for (Element e1 : e.getAllElements()) {
				if (e1.getAttributeValue("data-price-raw") != null) {
					//System.out.println("A:" + e1.toString());
					if(e1.getAttributeValue("value").equals("1")){
						System.out.println(e1.getAttributeValue("data-price-raw"));
						priceList.add(e1.getAttributeValue("data-price-raw"));
					}
				}
			}
		}*/
		
		for(int i=0;i<roomTypeList.size();i++)
		{
			String type = roomTypeList.get(i);
			String price = priceList.get(i);
			RoomPrice rp = new RoomPrice("BookingDotCom",hotelName,"",checkInDate,type,price);
			rp.setBreakfastIncluded(false);
			rp.setFreeCancle(freeCancle.get(i));
			
			retList.add(rp);
		}
		return retList;
	}

}
