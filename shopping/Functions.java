package shopping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Functions
{
	private ArrayList<Food> foodList = new ArrayList<Food> ();
	private Scanner scanner = new Scanner(System.in);

	class NameComparator implements Comparator<Food>
	{
		@Override
		public int compare(Food f1, Food f2)
		{
			return f1.getName().compareTo(f2.getName());
		}
	}
	
	class LocationComparator implements Comparator<Food>
	{
		@Override
		public int compare(Food f1, Food f2)
		{
			if (f1.getLocation().compareTo(f2.getLocation()) > 0)
			{
				return 1;
			}
			else if (f1.getLocation().compareTo(f2.getLocation()) < 0)
			{
				return -1;
			}
			else
			{
				if (f1.getName().compareTo(f2.getName()) > 0)
						return 1;
				else
						return -1;
			}
		}
	}
	
	private void display()
	{
		for (int i = 0; i < foodList.size(); i++)
		{
			System.out.println(foodList.get(i).getName() + "\tLocation: " + foodList.get(i).getLocation());
		}
	}
	
	private void addFood()
	{
		System.out.print("Name of food: ");
		String name = scanner.nextLine();
		System.out.print("Location of food: (Enter unknown if not known): ");
		String location = scanner.nextLine();
		Food food = new Food(name, location);
		foodList.add(food);
	}
	
	private void removeFood()
	{
		boolean found = false;
		int i = 0;
		System.out.print("Name of food to remove: ");
		String name = scanner.nextLine();
		while ((found != true) && i <= foodList.size())
		{
			if (foodList.get(i).getName().toLowerCase().equals(name.toLowerCase()))
			{
				foodList.remove(i);
				found = true;
			}
			i++;
		}
	}
	
	private void sortList(String which)
	{
		switch (which.toLowerCase())
		{
			case "name":
				Collections.sort(foodList, new NameComparator());
				break;
			case "location":
				Collections.sort(foodList, new LocationComparator());
				break;
			default:
				System.out.println("Invalid sort (somehow). Tried to sort by: " + which);
		}
	}
	
	public void getFunction(int which)
	{
		switch (which)
		{
			case 1:
				addFood();
				break;
			case 2:
				sortList("name");
				System.out.println("Sorted by name");
				break;
			case 3:
				sortList("location");
				System.out.println("Sorted by location");
				break;
			case 4:
				display();
				break;
			case 5:
				removeFood();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid input.");
		}
	}
	
	public void displayMenu()
	{
		int which = 0;
		while (which != 5)
		{
			System.out.println("Please enter the number for which you would like to do:");
			System.out.println("1. Add food");
			System.out.println("2. Sort list by name");
			System.out.println("3. Sort list by location");
			System.out.println("4. Display list");
			System.out.println("5. Remove food");
			System.out.println("6. End program");
			
			if (scanner.hasNextInt())
			{
				which = scanner.nextInt();
				scanner.nextLine();
			}
			else
			{
				scanner.next();
				which = 0;
			}
			getFunction(which);
			System.out.println();
		}
	}
}
