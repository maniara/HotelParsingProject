package HotesIdentifier;

public abstract class HotelIdentifier {
	public String ninetree;
	public String sejong;
	public String pacific;
	public String manu;
	public String shinshin;
	public String skypark;
	
	public String getNinetree() {
		return ninetree;
	}
	public abstract void setNinetree();
	public String getSejong() {
		return sejong;
	}
	public abstract void setSejong();
	public String getPacific() {
		return pacific;
	}
	public abstract void setPacific();
	public String getManu() {
		return manu;
	}
	public abstract void setManu();
	public String getShinshin() {
		return shinshin;
	}
	public abstract void setShinshin();
	public String getSkypark() {
		return skypark;
	}
	public abstract void setSkypark();
	
	public HotelIdentifier()
	{
		setNinetree();
		setSejong();
		setPacific();
		setManu();
		setShinshin();
		setSkypark();
	}
	
	
}
