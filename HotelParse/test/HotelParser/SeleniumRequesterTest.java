package HotelParser;

import org.junit.Test;

import RequestHandler.SeleniumRequester;

public class SeleniumRequesterTest {
	
	@Test
	public void getResponseTest()
	{
		String url = "https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016.07.13&chkout=2016.07.14";
		String source = SeleniumRequester.getResponse(url);
		System.out.println(source);
	}

}
