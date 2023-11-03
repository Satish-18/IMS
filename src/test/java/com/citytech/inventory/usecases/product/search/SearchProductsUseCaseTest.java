package com.citytech.inventory.usecases.product.search;

import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class SearchProductsUseCaseTest {
    private SearchProductsUseCase searchProductsUseCase;
    @BeforeEach
    public void setUp() {
        List<ProductEntity> productEntity= new ArrayList<>();
        productEntity.add(new ProductEntity("1","mobile","electronics",12));
        productEntity.add(new ProductEntity("2","ball","sports",15));
        productEntity.add(new ProductEntity("3","tv","electronics",5));
        EntityManager.setProductList(productEntity);
        searchProductsUseCase=new SearchProductsUseCase();
    }
    @Test
    public void shouldReturnMatchingProducts() {
        SearchProductsRequest request= new SearchProductsRequest("mobile");
        Optional<SearchProductsResponse> response=searchProductsUseCase.execute(request);
        assertTrue(response.isPresent());
        assertEquals("mobile",response.get().productEntity().get(0).name());
    }
    @Test
    public void shouldNotBeCaseSensitive() {
        SearchProductsRequest request= new SearchProductsRequest("MOBile");
        Optional<SearchProductsResponse> response=searchProductsUseCase.execute(request);
        assertTrue(response.isPresent());
        assertEquals("mobile",response.get().productEntity().get(0).name());
    }
    @Test
    public void shouldGiveEmptyWhenSearchedItemIsNotPresent() {
        SearchProductsRequest request= new SearchProductsRequest("car");
        Optional<SearchProductsResponse> response=searchProductsUseCase.execute(request);
        assertTrue(response.isPresent());
        assertTrue(response.get().productEntity().isEmpty());

    }
    // need to check this
    @Test
    public void shouldGiveEmptyWhenSearchedItemIsEmpty() {
        SearchProductsRequest request= new SearchProductsRequest(null);
        Optional<SearchProductsResponse> response=searchProductsUseCase.execute(request);
        assertTrue(response.isPresent());
        assertTrue(response.get().productEntity().isEmpty());

    }
    
    




}