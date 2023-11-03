package com.citytech.inventory.usecases.category.update;

import com.citytech.inventory.usecases.product.update.UpdateProductRequest;
import com.citytech.inventory.usecases.product.update.UpdateProductResponse;
import com.citytech.inventory.usecases.product.update.UpdateProductUseCase;
import com.citytech.repositories.CategoryEntity;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class UpdateCategoryUseCaseTest {
    UpdateCategoryUseCase updateCategoryUseCase;
    @BeforeEach
    public void setUp() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(new CategoryEntity("1", "Electronics", "This is Electronics"));
        categoryEntities.add(new CategoryEntity("2", "Sports", "This is Sports"));
        categoryEntities.add(new CategoryEntity("3", "Groceries", "This is Groceries"));
        EntityManager.setCategoriesList(categoryEntities);
        updateCategoryUseCase= new UpdateCategoryUseCase();
    }
    @Test
    public void shouldUpdateCategoryWithRightId() {
        UpdateCategoryRequest request = new UpdateCategoryRequest("1","Electronic","This is Electronic");
        Optional<UpdateCategoryResponse> response = updateCategoryUseCase.execute(request);
        assertTrue(response.isPresent());
        CategoryEntity updatedCategory= EntityManager.getCategoriesList().get(0);
        assertEquals("1",updatedCategory.id());
        assertEquals("Electronic",updatedCategory.name());
        assertEquals("This is Electronic",updatedCategory.description());


    }

}