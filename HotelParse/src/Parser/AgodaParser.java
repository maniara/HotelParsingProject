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

public class AgodaParser {
	public static ArrayList<RoomPrice> getRooms(String hotelName, String hotelId, String checkInDate,
			String checkOutDate) throws MalformedURLException, IOException {

		ArrayList<RoomPrice> roomPriceList = new ArrayList<RoomPrice>();

		// http://www.agoda.com/ko-kr/nine-tree-hotel-myeong-dong/hotel/seoul-kr.html?asq=u2qcKLxwzRU5NDuxJ0kOFzzMnA%2bOamSu%2ffpUmp7dpwoJWuxG5MGtEhd1mcug%2f3JNF24%2bSMTczYXbdt%2bQXrn9zmvmUvdL2n6gimuIhTDTJjDaHr6mWYkscVkxnloeI%2bzAmgkJDRWyXYs%2bhuEHG%2bLQIIJ7pIpYNf0lxAtiP9ft%2fZ5Zy22DvgBpHXJhAxrS7SreCL4TBJE9pye3xXFa3JCPPuNXkMrN4L8GSQsLfM4C6EPi93LUom3IjiMssHq74%2bUgNXO5wfCxlIYO0n1kD0fR5Q%3d%3d&tick=636009056042&pagetypeid=1&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn=2016-6-25&checkout=2016-6-26&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true
		// http://www.agoda.com/ko-kr/nine-tree-hotel-myeong-dong/hotel/seoul-kr.html?asq=u2qcKLxwzRU5NDuxJ0kOFzzMnA%2bOamSu%2ffpUmp7dpwoJWuxG5MGtEhd1mcug%2f3JNF24%2bSMTczYXbdt%2bQXrn9zmvmUvdL2n6gimuIhTDTJjDaHr6mWYkscVkxnloeI%2bzAmgkJDRWyXYs%2bhuEHG%2bLQIIJ7pIpYNf0lxAtiP9ft%2fZ5Zy22DvgBpHXJhAxrS7SreCL4TBJE9pye3xXFa3JCPPuNXkMrN4L8GSQsLfM4C6EPi93LUom3IjiMssHq74%2bUgNXO5wfCxlIYO0n1kD0fR5Q%3d%3d&tick=636009056042&pagetypeid=1&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn=2016-6-25&checkout=2016-6-26&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true
		// http://www.agoda.com/sejong-hotel-seoul-myeongdong/hotel/seoul-kr.html?asq=u2qcKLxwzRU5NDuxJ0kOF8Ng%2fKH11q32Y5Bm%2fbO6IgN3X7XWlHdtZxPyeq0ofPGvF24%2bSMTczYXbdt%2bQXrn9zko7oeyKlWkH3h6ChIuC1l%2bwaDqN0kE79R8FX2eiipvewqu5Hgm33hok2WZqB1583IXyV0zBdaNg93f%2fO92XspdeDwra9TSGNM%2fYfbw9lcjZHlZVgAb7af%2ftTuHzPiuQl222IQLX1WPksKVIOcf0AOHQ7YF5A%2fEIfHNN11f3HZh%2fYjFAtTLYa9%2f7xNZhJyNklg%3d%3d&tick=636009150435&pagetypeid=1&origin=KR&cid=-1&htmlLanguage=en-us&checkIn=2016-6-25&checkout=2016-6-26&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true
		//
		String url = "http://www.agoda.com/ko-kr/" + hotelId
				+ "/hotel/seoul-kr.html?pagetypeid=7&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn=" + checkInDate
				+ "&checkout=" + checkOutDate + "&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true";

		// if the return price is not KRW, success is false
		Source source = new Source(SeleniumRequester.getAgodaResponse(url));
		// Source source = new Source(new URL(url));

		// System.out.println(source.getFirstElementByClass("currency
		// anchor").getContent().toString());
		// check the currency

		// parsing if the currency is KRW
		for (Element e1 : source.getAllElements()) {
			if (e1.getAttributeValue("data-selenium") == null)
				continue;

			// each room types are starting here
			if (e1.getAttributeValue("data-selenium").equals("room-item")) {

				boolean includedBreakfast = false;
				// cannot find cancle impossible case
				boolean freeCancle = true;

				// breakfast include check
				for (Element subE : e1.getAllElements()) {
					if (subE.getAttributeValue("data-selenium") != null) {
						if (subE.getAttributeValue("data-selenium").equals("breakfast-included")) {
							includedBreakfast = true;
							break;
						}
					}
				}

				String roomType;
				String price;
				String typeKor;
				String typeEng;

				roomType = e1.getAttributeValue("data-room-name");
				Element priceElement = e1.getFirstElementByClass("sellprice purple");
				price = priceElement.getContent().toString().trim().replace(",", "");

				RoomPrice rp = new RoomPrice("Agoda", hotelName, checkInDate, roomType, roomType, price);
				rp.setBreakfastIncluded(includedBreakfast);
				rp.setFreeCancle(freeCancle);

				roomPriceList.add(rp);
			}
		}

		return roomPriceList;
	}
}
