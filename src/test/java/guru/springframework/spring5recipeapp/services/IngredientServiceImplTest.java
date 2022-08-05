package guru.springframework.spring5recipeapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;

public class IngredientServiceImplTest {
	
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	IngredientService ingredientService;

	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand, unitOfMeasureRepository, ingredientCommandToIngredient);
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() {
		//given
		Recipe recipe = new Recipe();
		recipe.setId("1");
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId("1");
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId("2");
		
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId("3");
		
		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);
		
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		
		//when
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3");
		
		//then
		assertEquals("3", ingredientCommand.getId());
		verify(recipeRepository, times(1)).findById(anyString());
		
	}
	
	@Test
	public void testSaveIngredientCommand() throws Exception {
		//given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId("3");
		ingredientCommand.setRecipeId("2");
		
		Optional<Recipe> recipeOptional = Optional.of(new Recipe());
		
		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId("3");
		
		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);
		
		//when
		 IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);
		 
		 //then
		 assertEquals("3", savedIngredientCommand.getId());
		 verify(recipeRepository, times(1)).findById(anyString());
		 verify(recipeRepository, times(1)).save(any());
	}
	
	@Test
	public void testDeleteById() {
		//given
		Recipe recipe = new Recipe();
		Ingredient ingredient = new Ingredient();
		ingredient.setId("3");
		recipe.addIngredient(ingredient);

		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		
		//when
		ingredientService.deleteById("1", "3");
		
		//then
		verify(recipeRepository, times(1)).findById(anyString());
		verify(recipeRepository, times(1)).save(any());
		
	}

}
