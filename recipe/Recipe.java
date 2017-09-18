package recipe;

import java.util.ArrayList;

public class Recipe
{
	//Test 1 2 3
	private String name;
	private String type;
	private ArrayList<String> ingredients;
	
	public Recipe(String name, String type, ArrayList<String> ingredients)
	{
		this.name = name;
		this.type = type;
		this.ingredients = ingredients;
	}
	
	public void changeType(String type)
	{
		this.type = type;
	}
	
	public void changeIngredients(ArrayList<String> ingredients)
	{
		this.ingredients = ingredients;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public ArrayList<String> getIngredients()
	{
		return ingredients;
	}
	
	public String toString()
	{
		return name + "," + type + "," + ingredients;
	}
	
	public String display()
	{
		return name + "\t Type: " + type + "\nIngredients:\n" + ingredients;
	}
}
