package com.citytech.inventory.usecases.category.get;

import com.citytech.inventory.usecases.product.get.GetAllProductsRequest;
import com.citytech.inventory.usecases.product.get.GetAllProductsResponse;
import com.citytech.inventory.usecases.product.get.GetAllProductsUseCase;
import com.citytech.repositories.CategoryEntity;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@MicronautTest
class GetAllCategoriesUseCaseTest {
    GetAllCategoriesUseCase getAllCategoriesUseCase;

    @BeforeEach
    public void setUp() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(new CategoryEntity("1", "Electronics", "This is Electronics"));
        categoryEntities.add(new CategoryEntity("2", "Sports", "This is Sports"));
        categoryEntities.add(new CategoryEntity("3", "Groceries", "This is Groceries"));
        EntityManager.setCategoriesList(categoryEntities);
        getAllCategoriesUseCase= new GetAllCategoriesUseCase();
    }
    @Test
    public void shouldFetchAllCategories() {
        Optional<GetAllCategoriesResponse> response = getAllCategoriesUseCase.execute(new GetAllCategoriesRequest());
        assertTrue(response.isPresent());
        assertEquals(EntityManager.getCategoriesList().size(), 3);
    }



}