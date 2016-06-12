package RequestHandler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AgodaRequester {
	public static String sendPost(String hotelName, String checkInDate, String checkOutDate) throws IOException
	{
		//158628, 135.09
//		String url = "http://www.agoda.com/ko-kr/" + hotelName
//				+ "/hotel/seoul-kr.html?pagetypeid=7&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn=" + checkInDate
//				+ "&checkout=" + checkOutDate + "&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true";
		
		String url = "http://www.agoda.com/ko-kr/"+hotelName+"/hotel/seoul-kr.html";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		String urlParameters = "pagetypeid=1&origin=KR&cid=-1&htmlLanguage=ko-kr&checkIn="+checkInDate+"&checkout="+checkOutDate+"&los=1&rooms=1&adults=2&childs=0&isFromSearchBox=true";
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
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
		return response.toString();
	}

}
