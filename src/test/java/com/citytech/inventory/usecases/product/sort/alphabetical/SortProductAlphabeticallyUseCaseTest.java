package com.citytech.inventory.usecases.product.sort.alphabetical;

import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class SortProductAlphabeticallyUseCaseTest {
    private SortProductAlphabeticallyUseCase sortProductAlphabeticallyUseCase;

    @BeforeEach
    public void setUp() {
        List<ProductEntity> productEntity= new ArrayList<>();
        productEntity.add(new ProductEntity("1","mobile","electronics",12));
        productEntity.add(new ProductEntity("2","ball","sports",15));
        productEntity.add(new ProductEntity("3","tv","electronics",5));
        EntityManager.setProductList(productEntity);
        sortProductAlphabeticallyUseCase=new SortProductAlphabeticallyUseCase();
    }

    @Test
    public void shouldReturnSortedListOfProductsWhenProductsExist() {
        Optional<SortProductAlphabeticallyResponse> response=sortProductAlphabeticallyUseCase.execute(new SortProductAlphabeticallyRequest());
        List<String> productNames = response.get().sortedList().stream().map(ProductEntity::name).collect(Collectors.toList());
        assertEquals(Arrays.asList("ball", "mobile", "tv"), productNames);

    }
    @Test
    public void shouldReturnEmptyListWhenNoProductsExist() {
        EntityManager.setProductList(Collections.emptyList());
        Optional<SortProductAlphabeticallyResponse> response = sortProductAlphabeticallyUseCase.execute(new SortProductAlphabeticallyRequest());
        assertTrue(response.isPresent());
        assertTrue(response.get().sortedList().isEmpty());
    }

}