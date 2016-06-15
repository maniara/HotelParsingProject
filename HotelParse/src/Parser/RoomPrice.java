package Parser;

import java.util.Date;

public class RoomPrice {
	
	private String sourceSite;
	private String hotelName;
	private String checkInDate;
	private String roomTypeEng;
	private String roomTypeKor;
	private String priceKRW;
	private boolean freeCancle;
	private boolean breakfastIncluded;
	private String createDate;
	
	
	
	public RoomPrice(String sourceSite, String hotelName, String checkInDate, String roomTypeEng, String roomTypeKor,
			String priceKRW) {
		super();
		this.sourceSite = sourceSite;
		this.hotelName = hotelName;
		this.checkInDate = checkInDate;
		this.roomTypeEng = roomTypeEng;
		this.roomTypeKor = roomTypeKor;
		this.priceKRW = priceKRW;
		this.createDate = new Date().toString();
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



	public boolean isFreeCancle() {
		return freeCancle;
	}



	public void setFreeCancle(boolean freeCancle) {
		this.freeCancle = freeCancle;
	}



	public boolean isBreakfastIncluded() {
		return breakfastIncluded;
	}



	public void setBreakfastIncluded(boolean breakfastIncluded) {
		this.breakfastIncluded = breakfastIncluded;
	}



	public String getCreateDate() {
		return createDate;
	}



	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



	@Override
	public String toString()
	{
		String cancle = "No cancle";
		String bk = "No breakfast";
		if(freeCancle)
			cancle="Free cancle";
		if(this.breakfastIncluded)
			bk = "Breakfast inluded";
		return sourceSite+";"+hotelName+";"+checkInDate+";"+roomTypeKor+";"+priceKRW+";"+cancle+";"+bk;
	}
	

}
