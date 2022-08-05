package guru.springframework.spring5recipeapp.services;

import java.util.Set;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
	
	Recipe findById(String id);
	
	RecipeCommand savedRecipeCommand(RecipeCommand command);
	
	RecipeCommand findCommandById(String id);
	
	void deleteById(String id);
}
