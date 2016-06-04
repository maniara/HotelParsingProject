package HotelParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ExpediaRequester {
	public void getExpediaRespose() throws IOException
	{
		String url = "https://www.expedia.co.kr/Seoul-Hotels-Nine-Tree-Hotel.h6084370.Hotel-Information?chkin=2016.06.13&chkout=2016.06.14&rm1=a2&hwrqCacheKey=b8bbe2f0-0d2f-41ec-9db8-ca025f4530f0HWRQ1464857938255&c=30966cb8-c3d9-4276-bc5c-5a3311cfe3d7&";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
	}
}

