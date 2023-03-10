package seedu.meal360;

import java.util.HashMap;
import java.util.Scanner;

public class Parser {

    public String[] parseCommand(String command) {
        return command.split(" ");
    }

    public Recipe parseAddRecipe(String[] input, RecipeList recipeList) {
        String recipeName = input[1].substring(2);
        System.out.println("Please Enter The Ingredients & Quantity: ");
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String line = userInput.nextLine();
            if (line.equals("done")) {
                break;
            }
            String[] command = line.trim().split(" ");
            ingredients.put(command[0], Integer.parseInt(command[1]));
        }
        userInput.close();
        Recipe newRecipe = new Recipe(recipeName, ingredients);
        recipeList.addRecipe(newRecipe);
        recipeList.add(newRecipe);
        return newRecipe;
    }

    public Recipe parseAddRecipe(String[] input) {
        return new Recipe("test", null);
    }

    public Recipe parseEditRecipe(String[] input, RecipeList recipeList) {
        String recipeName = input[1].substring(2);
        Recipe recipeToEdit;
        if (recipeList.findByName(recipeName) == null) {
            // ui recipe not found
            return null;
        } else {
            recipeToEdit = recipeList.findByName(recipeName);
        }
        System.out.println("Please Enter New Ingredients & Quantity: ");
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String line = userInput.nextLine();
            if (line.equals("done")) {
                break;
            }
            String[] command = line.trim().split(" ");
            ingredients.put(command[0], Integer.parseInt(command[1]));
        }
        userInput.close();
        recipeList.editRecipe(recipeToEdit, ingredients);
        return recipeToEdit;
    }

    public Recipe parseDeleteRecipe(String[] input, RecipeList recipeList) {
        // user inputted recipe name
        if (input[1].contains("r/")) {
            // skip over /r in recipe name
            String recipeName = input[1].substring(2);
            int recipeIndex = 1;
            for (Recipe recipe : recipeList) {
                // find index of recipe we want to delete
                if (recipe.getName().equals(recipeName)) {
                    break;
                }
                recipeIndex++;
            }
            return recipeList.deleteRecipe(recipeIndex);
            // user inputted index of recipe in list
        } else {
            int recipeIndex = Integer.parseInt(input[1]);
            return recipeList.deleteRecipe(recipeIndex);
        }
    }

    public String parsePrepareRecipe(String[] input) {
        return "test";
    }

    public void parseListRecipe(RecipeList recipeList) {
        recipeList.listRecipes();
    }

    public Recipe parseViewRecipe(String[] command, RecipeList recipes) {
        int recipeIndex = Integer.parseInt(command[1]) - 1;
        return recipes.get(recipeIndex);
    }

    public RecipeList parseLoadDatabase(String input) {
        return new RecipeList();
    }

    public String parseSaveDatabase(String input) {
        return "test";
    }
}
