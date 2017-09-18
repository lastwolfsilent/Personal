package shopping;

public class Food
{
	//Test 1 2 3
	private String name;
	private String location;
		
/********************   CONSTRUCTOR   ********************/
	public Food(String name, String location)
	{
		this.name = name;
		this.location = location;
	}
	
/********************   SETTERS   ********************/
	public void setLocation(String location)
	{
		this.location = location;
	}
	
/********************   GETTERS   ********************/	
	public String getName()
	{
		return name;
	}
	
	public String getLocation()
	{
		return location;
	}
}
