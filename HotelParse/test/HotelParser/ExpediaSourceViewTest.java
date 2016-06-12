package HotelParser;

import java.io.IOException;

import org.junit.Test;

import Parser.ExpediaSourceView;

public class ExpediaSourceViewTest {
	
	@Test
	public void sourceViewTest()
	{
		try {
			new ExpediaSourceView().viewSource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
