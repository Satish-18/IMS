package com.citytech.inventory.usecases.product.get;

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
class GetAllProductsUseCaseTest {
    GetAllProductsUseCase getAllProductsUseCase;

    @BeforeEach
    public void setUp() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("1", "Mobile Phone", "Electronics", 10));
        productEntities.add(new ProductEntity("2", "Laptop", "Electronics", 5));
        productEntities.add(new ProductEntity("3", "Laptop", "Sports", 15));
        EntityManager.setProductList(productEntities);
        getAllProductsUseCase= new GetAllProductsUseCase();
    }
    @Test
    public void shouldProvideAllProduct() {
        Optional<GetAllProductsResponse> response = getAllProductsUseCase.execute(new GetAllProductsRequest());
        assertTrue(response.isPresent());
        assertEquals(EntityManager.getProductList(), response.get().productsList());
    }


}