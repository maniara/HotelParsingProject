package HotelParser;

import java.io.IOException;

import org.junit.Test;

import HotelParse.ExpediaRequester;

public class ExpediaRequesterTest {
	@Test
	public void getRequestTest() throws IOException
	{
		new ExpediaRequester().getExpediaRespose();
	}

}
