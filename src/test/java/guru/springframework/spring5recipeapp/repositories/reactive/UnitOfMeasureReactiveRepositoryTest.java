package guru.springframework.spring5recipeapp.repositories.reactive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import guru.springframework.spring5recipeapp.bootstrap.DataLoader;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import reactor.core.publisher.Mono;

@DataMongoTest
class UnitOfMeasureReactiveRepositoryTest {
	
	public static final String CUP = "Cup";
	
	@Autowired
	UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
	

	@BeforeEach
	public void setUp() throws Exception {
		unitOfMeasureReactiveRepository.deleteAll().block();
	}

	@Test
	public void testUnitOfMeasureSave() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(CUP);
		
		unitOfMeasureReactiveRepository.save(uom).block();
		
		Long count = unitOfMeasureReactiveRepository.count().block();
		
		assertEquals(Long.valueOf(1L), count);
	}
	
	@Test
	public void testFindByDescription() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription(CUP);
		
		unitOfMeasureReactiveRepository.save(uom).block();
		
		UnitOfMeasure fetchedUom = unitOfMeasureReactiveRepository.findByDescription(CUP).block();
		
		assertEquals(CUP, fetchedUom.getDescription());
	}

}
