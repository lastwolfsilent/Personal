package recipe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Functions
{
	private ArrayList<Recipe> recipeList = new ArrayList<> ();
	private Scanner scanner = new Scanner(System.in);
	
	public void displayMenu()
	{
		getList();
		int which = 0;
		while (which != 4)
		{
			System.out.println("1. Add Recipe");
			System.out.println("2. Display List");
			System.out.println("3. Display Recipe");
			System.out.println("4. Remove Recipe");
			System.out.println("5. Random Recipe");
			System.out.println("6. End File");
			
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
	
	private void getFunction(int which)
	{
		switch (which)
		{
			case 1:
				addRecipe();
				break;
			case 2:
				displayList();
				break;
			case 3:
				displayRecipe();
				break;
			case 4:
				removeRecipe();
				break;
			case 5:
				randomRecipe();
				break;
			case 6:
				saveList();
				System.exit(0);
			default:
				System.out.println("Invalid getFunction #. Tried to get: " + which);
		}
	}
	
	private void displayList()
	{
		for (Recipe recipe : recipeList)
		{
			System.out.println(recipe.getName());
		}
	}
	
	private void displayRecipe()
	{
		System.out.println("Enter recipe to display");
		String input = scanner.nextLine();
		boolean found = false;
		
		for (Recipe recipe : recipeList)
		{
			if (recipe.getName().toLowerCase().equals(input.toLowerCase()))
			{
				System.out.println(recipe.display());
				found = true;
			}
		}
		
		if (!found)
		{
			System.out.println("Could not find recipe: " + input);
		}
	}
	
	private void addRecipe()
	{
		String name;
		String type;
		ArrayList<String> ingredients = new ArrayList<> ();
		System.out.println("Enter the name of the recipe:");
		name = scanner.nextLine();
		
		System.out.println("Enter the type of recipe:");
		type = scanner.nextLine();
		
		String input = null;
		System.out.println("Type the ingredients for the recipe:");
		System.out.println("Use enter after every ingredient and type done when complete:");
		input = scanner.nextLine();
		while (!input.toLowerCase().equals("done"))
		{
			ingredients.add(input);
			input = scanner.nextLine();
		}
		
		Recipe recipe = new Recipe(name, type, ingredients);
		recipeList.add(recipe);
	}
	
	private void removeRecipe()
	{
		System.out.println("Type which recipe to remove:");
		String input = scanner.nextLine();
		boolean found = false;
		
		for (int i = 0; i < recipeList.size(); i++)
		{
			if (input.toLowerCase().equals(recipeList.get(i).getName().toLowerCase()))
			{
				recipeList.remove(i);
				found = true;
				break;
			}
		}
		
		if (!found)
		{
			String again = null;
			System.out.println("Could not find the recipe. Would you like to try to remove another or try again? y/n");
			while (true)
			{
				again = scanner.nextLine();
				if (again.toLowerCase().equals("y"))
				{
					removeRecipe();
					break;
				}
				else if (again.toLowerCase().equals("n"))
					break;
				else
					System.out.println("That is not a correct input. Please enter y or n");
			}
		}
	}
	
	private Recipe randomRecipe()
	{
		Random rand = new Random();
		int which = rand.nextInt(recipeList.size()) + 1;
		
		System.out.println(recipeList.get(which - 1).display());
		
		return null;
	}
	
	private Recipe randomRecipeType()
	{
		return null;
	}
	
	private void getList()
	{
		try (BufferedReader br = new BufferedReader(new FileReader("grocery_list.txt")))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				List<String> list = Arrays.asList(line.split(","));
				ArrayList<String> ingList = new ArrayList<> ();		//Ingredient List
				for (int i = 2; i < list.size(); i++)
				{
					ingList.add(list.get(i));
				}
				Recipe recipe = new Recipe(list.get(0), list.get(1), ingList);
				recipeList.add(recipe);
			}
			
		} 
		catch (FileNotFoundException e)
		{
		} 
		catch (IOException e)
		{
		}
	}
	
	private void saveList()
	{
		try
		{
			PrintWriter pw = new PrintWriter("grocery_list.txt","UTF-8");
			for(Recipe recipe : recipeList)
			{
				pw.println(recipe.toString());
			}
			pw.close();
		}
		catch (IOException e)
		{
			System.out.println("Cannot find/create file 'recipe_list.txt'");
		}
	}
}
