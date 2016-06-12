package HotelParser;

import java.io.IOException;

import org.junit.Test;

import RequestHandler.AgodaRequester;

public class AgodaRequesterTest {
	@Test
	public void getPostTest() throws IOException{
		AgodaRequester.sendPost("nine-tree-hotel-myeong-dong","2016-6-25","2016-6-26");
	}

}
