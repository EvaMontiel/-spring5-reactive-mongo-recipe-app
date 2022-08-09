package guru.springframework.spring5recipeapp.repositories.reactive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import guru.springframework.spring5recipeapp.domain.Category;

@DataMongoTest
class CategoryReactiveRepositoryTest {
	
	public static final String FAST_FOOD = "Fast Food";
	
	@Autowired
	CategoryReactiveRepository categoryReactiveRepository;

	@BeforeEach
	public void setUp() throws Exception {
		categoryReactiveRepository.deleteAll().block();
	}
	
	@Test
	public void testSaveCategory() {
		Category category = new Category();
		category.setDescription(FAST_FOOD);
		
		categoryReactiveRepository.save(category).block();
		
		Long count = categoryReactiveRepository.count().block();
		
		assertEquals(Long.valueOf(1L), count);
	}

	@Test
	public void testFindByDescription() {
		Category category = new Category();
		category.setDescription(FAST_FOOD);
		
		categoryReactiveRepository.save(category).block();
		
		Category fetchedCategory = categoryReactiveRepository.findByDescription(FAST_FOOD).block();
		
		assertEquals(FAST_FOOD, fetchedCategory.getDescription());
	}

}
