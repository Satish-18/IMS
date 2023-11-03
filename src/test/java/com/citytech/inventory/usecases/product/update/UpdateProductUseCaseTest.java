package com.citytech.inventory.usecases.product.update;

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
class UpdateProductUseCaseTest {
    UpdateProductUseCase updateProductUseCase;
    @BeforeEach
    public void setUp() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("1", "Mobile Phone", "Electronics", 10));
        productEntities.add(new ProductEntity("2", "Laptop", "Electronics", 5));
        productEntities.add(new ProductEntity("3", "Soccer Ball", "Sports", 15));
        EntityManager.setProductList(productEntities);
        updateProductUseCase= new UpdateProductUseCase();
    }
    @Test
    public void shouldUpdateProductWithRightId() {
        UpdateProductRequest request = new UpdateProductRequest("1","Phone","Electronics",11);
        Optional<UpdateProductResponse> response = updateProductUseCase.execute(request);
        assertTrue(response.isPresent());
        ProductEntity updatedProduct= EntityManager.getProductList().get(0);
        assertEquals("1",updatedProduct.id());
        assertEquals("Phone",updatedProduct.name());
        assertEquals("Electronics",updatedProduct.category());
        assertEquals(11,updatedProduct.quantity());

    }


}