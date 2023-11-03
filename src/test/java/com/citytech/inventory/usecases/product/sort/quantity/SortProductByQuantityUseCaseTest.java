package com.citytech.inventory.usecases.product.sort.quantity;

import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class SortProductByQuantityUseCaseTest {
    private SortProductByQuantityUseCase sortProductByQuantityUseCase;
    @BeforeEach
    public void setUp() {
        List<ProductEntity> productEntity= new ArrayList<>();
        productEntity.add(new ProductEntity("1","mobile","electronics",12));
        productEntity.add(new ProductEntity("2","ball","sports",15));
        productEntity.add(new ProductEntity("3","tv","electronics",5));
        EntityManager.setProductList(productEntity);
        sortProductByQuantityUseCase=new SortProductByQuantityUseCase();
    }

    @Test
    public void shouldReturnSortedListOfProductsWhenProductsExist() {
        Optional<SortProductByQuantityResponse> response = sortProductByQuantityUseCase.execute(new SortProductByQuantityRequest());
        List<Long> productQuantity = response.get().sortedList().stream().map(ProductEntity::quantity).collect(Collectors.toList());
        assertEquals(Arrays.asList(5L, 12L, 15L), productQuantity);

    }
    @Test
    public void shouldReturnEmptyListWhenNoProductsExist() {
        EntityManager.setProductList(Collections.emptyList());
        Optional<SortProductByQuantityResponse> response = sortProductByQuantityUseCase.execute(new SortProductByQuantityRequest());
        assertTrue(response.isPresent());
        assertTrue(response.get().sortedList().isEmpty());
    }

}