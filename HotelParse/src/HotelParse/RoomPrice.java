package HotelParse;

public class RoomPrice {
	
	private String sourceSite;
	private String hotelName;
	private String checkInDate;
	private String roomTypeEng;
	private String roomTypeKor;
	private String priceKRW;
	
	
	
	public RoomPrice(String sourceSite, String hotelName, String checkInDate, String roomTypeEng, String roomTypeKor,
			String priceKRW) {
		super();
		this.sourceSite = sourceSite;
		this.hotelName = hotelName;
		this.checkInDate = checkInDate;
		this.roomTypeEng = roomTypeEng;
		this.roomTypeKor = roomTypeKor;
		this.priceKRW = priceKRW;
	}



	public RoomPrice(String sourceSite, String hotelName, String checkInDate) {
		this.sourceSite = sourceSite;
		this.hotelName = hotelName;
		this.checkInDate = checkInDate;
	}



	public String getSourceSite() {
		return sourceSite;
	}



	public void setSourceSite(String sourceSite) {
		this.sourceSite = sourceSite;
	}



	public String getHotelName() {
		return hotelName;
	}



	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}



	public String getCheckInDate() {
		return checkInDate;
	}



	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}



	public String getRoomTypeEng() {
		return roomTypeEng;
	}



	public void setRoomTypeEng(String roomTypeEng) {
		this.roomTypeEng = roomTypeEng;
	}



	public String getRoomTypeKor() {
		return roomTypeKor;
	}



	public void setRoomTypeKor(String roomTypeKor) {
		this.roomTypeKor = roomTypeKor;
	}



	public String getPriceKRW() {
		return priceKRW;
	}



	public void setPriceKRW(String priceKRW) {
		this.priceKRW = priceKRW;
	}



	@Override
	public String toString()
	{
		return sourceSite+";"+hotelName+";"+checkInDate+";"+roomTypeKor+";"+priceKRW;
	}
	

}
