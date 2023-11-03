package com.citytech.inventory.usecases.product.filter;

import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class FilterByCategoryUseCaseTest {
    private FilterByCategoryUseCase filterByCategoryUseCase;

    @BeforeEach
    public void setUp() {
        List<ProductEntity> productEntity= new ArrayList<>();
        productEntity.add(new ProductEntity("1","mobile","electronics",12));
        productEntity.add(new ProductEntity("2","ball","sports",15));
        productEntity.add(new ProductEntity("3","tv","electronics",5));
        EntityManager.setProductList(productEntity);
        filterByCategoryUseCase=new FilterByCategoryUseCase();
    }
    @Test
    public void shouldReturnFilteredProductByCategory() {
        FilterByCategoryRequest request= new FilterByCategoryRequest("sports");
        Optional<FilterByCategoryResponse> response=filterByCategoryUseCase.execute(request);
        assertTrue(response.isPresent());
        assertEquals(1,response.get().filteredProducts().size());
        Assertions.assertEquals(List.of(new ProductEntity("2", "ball", "sports", 15)),response.get().filteredProducts());
    }


}