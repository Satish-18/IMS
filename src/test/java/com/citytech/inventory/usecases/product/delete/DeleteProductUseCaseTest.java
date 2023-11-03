package com.citytech.inventory.usecases.product.delete;

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
class DeleteProductUseCaseTest {
    DeleteProductUseCase deleteProductUseCase;


    @BeforeEach
    public void setUp() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("1", "Mobile Phone", "Electronics", 10));
        productEntities.add(new ProductEntity("2", "Laptop", "Electronics", 5));
        productEntities.add(new ProductEntity("3", "Soccer Ball", "Sports", 15));
        EntityManager.setProductList(productEntities);
        deleteProductUseCase= new DeleteProductUseCase();
    }
    @Test
    public void shouldDeleteProductWithRightId() {
       DeleteProductRequest request = new DeleteProductRequest("1");
        Optional<DeleteProductResponse> response = deleteProductUseCase.execute(request);
        assertTrue(response.isPresent());
        assertEquals("Product 1 Deleted Successfully",response.get().message());

    }


}