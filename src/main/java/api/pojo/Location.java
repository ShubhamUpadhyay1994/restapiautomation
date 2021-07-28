package api.pojo;

public class Location
{
	private double latitude;
	private double longitude;
	private double lat;
	private double lng;

	public Location(double latitude, double longitude, double lat, double lng)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.lat = lat;
		this.lng = lng;
	}

	public Location()
	{

	}

	public double getLat()
	{
		return lat;
	}

	public void setLat(double lat)
	{
		this.lat = lat;
	}

	public double getLng()
	{
		return lng;
	}

	public void setLng(double lng)
	{
		this.lng = lng;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

}
