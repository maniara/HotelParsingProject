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

public class BookingDotComParser {
	public static ArrayList<RoomPrice> getRooms(String hotelName, String checkInDate, String checkOutDate)
			throws MalformedURLException, IOException {

		ArrayList<RoomPrice> roomPriceList = new ArrayList<RoomPrice>();

		// String url =
		// "http://www.agoda.com/ko-kr/"+hotelName+"/hotel/seoul-kr.html?asq=r2grbjEASwGFtAW0rdj6nXG4s6hI%2fU6UzLzliGL7s2Qvee45iX9%2by9l4qcWSppbOu7380eYtA6P088u7B52Y5OoGPQGFw2APx1yVzBOrCDrGB49jKpm17msrzrMjaKms%2b0l33YdCWxhkjWx3Ys3Wm%2fLoDDgRc6WQkUOqVOxnO6d35htW7SeK1yBqrgehy5VjMWr9kwuA17ZV8N5ddxLteEqI7gGGHwQu3f5csAilUf%2bsuGhvXoqPvUpEDO0FFSMy&tick=635992835153&pagetypeid=7&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn="+checkInDate+"&checkout="+checkOutDate+"&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true";
		// String url = "http://sofar-val.appspot.com/Login.html";
		String url = "https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016.06.13&chkout=2016.06.14&";

		boolean success = false;
		while (success == false) {
			Source source = new Source(new URL(url));
			for (Element e1 : source.getAllElements()) {
				// System.out.println(e1.toString());
				if (e1.getAttributeValue("data-track") == null)
					continue;

				if (e1.getAttributeValue("data-track").equals("HOT.HIS.RoomsAndRates.ShowRoomInformation")) {
					
					if (e1.getContent().toString().contains("자세히 보기")) {
						//System.out.println("Failed");
						success = false;
						break;
					}
					else{
						success = true;
						System.out.println(e1.getContent());
					}

				}
				//e1.getFirstElementByClass("exp10264-1 free-cancellation-short free-text")
			}
		}

		return roomPriceList;
	}
}
