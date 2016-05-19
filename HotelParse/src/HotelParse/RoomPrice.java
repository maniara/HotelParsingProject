package HotelParse;

public class RoomPrice {
	
	private String checkInDate;
	private String roomType;
	private String price;
	
	public RoomPrice(String checkInDate, String roomType, String price) {
		super();
		this.checkInDate = checkInDate;
		this.roomType = roomType;
		this.price = price;
	}
	
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString()
	{
		return checkInDate+";"+roomType+";"+price;
	}
	

}
