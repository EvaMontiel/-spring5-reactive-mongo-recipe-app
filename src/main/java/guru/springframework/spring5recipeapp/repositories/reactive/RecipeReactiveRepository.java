package guru.springframework.spring5recipeapp.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import guru.springframework.spring5recipeapp.domain.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String>{

}
