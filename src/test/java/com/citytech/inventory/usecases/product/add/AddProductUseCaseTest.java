package com.citytech.inventory.usecases.product.add;

import com.citytech.inventory.usecases.product.search.SearchProductsUseCase;
import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.repositories.ProductEntity;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class AddProductUseCaseTest {
    AddProductUseCase addProductUseCase;
    @BeforeEach
    public void setUp() {
        addProductUseCase= new AddProductUseCase();
    }
    @Test
    public void shouldCreateProductWithRightProductData() {
        AddProductRequest request = new AddProductRequest("1", "Camera", "Electronics", 12);
        Optional<AddProductResponse> response = addProductUseCase.execute(request);
        assertTrue(response.isPresent());

    }

    @Test
    public void shouldNotCreateProductWithEmptyName() {
        AddProductUseCase addProductUseCase = new AddProductUseCase();
        AddProductRequest addProductRequest = new AddProductRequest("1", "", "Electronics", 12);
        Throwable throwable = assertThrows(ImsException.class, () -> addProductUseCase.execute(addProductRequest));
        assertEquals(ExceptionType.PRODUCT_NAME_NONEMPTY.getMessage(), throwable.getMessage());
    }

    @Test
    public void shouldNotCreateProductWithNeagtiveQuantity() {
        AddProductUseCase addProductUseCase = new AddProductUseCase();
        AddProductRequest addProductRequest = new AddProductRequest("1", "Camera", "Electronics", -12);
        Throwable throwable = assertThrows(ImsException.class, () -> addProductUseCase.execute(addProductRequest));
        assertEquals(ExceptionType.PRODUCT_QUANTITY_NEGATIVE.getMessage(), throwable.getMessage());
    }


}