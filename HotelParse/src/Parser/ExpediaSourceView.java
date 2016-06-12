package Parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

public class ExpediaSourceView {
	public static void viewSource() throws MalformedURLException, IOException {
		// String url =
		// "https://www.expedia.co.kr/"+hotelName+".h6084370.Hotel-Information?chkin="+checkInDate+"&chkout="+checkOutDate+"&rm1=a2#rooms-and-rates";
		// System.out.println(url);
		String url = "https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016.06.13&chkout=2016.06.14&rm1=a2&hwrqCacheKey=b8bbe2f0-0d2f-41ec-9db8-ca025f4530f0HWRQ1464857938255&c=30966cb8-c3d9-4276-bc5c-5a3311cfe3d7&";
		// if Null data comes flag became false

		Source source = new Source(new URL(url));
		
		for(Element e: source.getAllElements())
		{
			if(!e.toString().contains("script"))
				System.out.println(e.toString());
		}
			
	}

}
