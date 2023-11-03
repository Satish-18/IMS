package com.citytech.inventory.usecases.category.delete;

import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.CategoryEntity;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class DeleteCategoryUseCaseTest {
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @BeforeEach
    public void setUp() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        List<ProductEntity>productEntities=new ArrayList<>();
        categoryEntities.add(new CategoryEntity("1", "Electronics", "This is Electronics"));
        categoryEntities.add(new CategoryEntity("2", "Sports", "This is Sports"));
        categoryEntities.add(new CategoryEntity("3", "Groceries", "This is Groceries"));
        productEntities.add(new ProductEntity("1","ball","Sports",12));
        productEntities.add(new ProductEntity("1","mobile","Electronics",12));
        EntityManager.setCategoriesList(categoryEntities);
        deleteCategoryUseCase = new DeleteCategoryUseCase();
    }

    @Test
    public void shouldDeleteCategoryWithCorrectName() {
        DeleteCategoryRequest request = new DeleteCategoryRequest("Sports");
        Optional<DeleteCategoryResponse> response = deleteCategoryUseCase.execute(request);
        assertTrue(response.isPresent());
        assertEquals("Sports", response.get().name());
    }

    @Test
    public void shouldNotDeleteIfCategoryNameDoesNotExist() {
        DeleteCategoryRequest request = new DeleteCategoryRequest("non-existent-category");
        Throwable throwable = assertThrows(ImsException.class, () -> deleteCategoryUseCase.execute(request));
        assertEquals(ExceptionType.CATEGORY_DOES_NOT_EXIST.getMessage(), throwable.getMessage());
    }


}