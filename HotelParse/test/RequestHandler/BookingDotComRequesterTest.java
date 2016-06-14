package RequestHandler;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

public class BookingDotComRequesterTest {
	@Test
	public void sendPostTest() throws MalformedURLException, IOException
	{
		new BookingDotComRequester().sendPost();
	}

}
