package com.citytech.inventory.usecases.category.add;

import com.citytech.inventory.usecases.product.add.AddProductRequest;
import com.citytech.inventory.usecases.product.add.AddProductResponse;
import com.citytech.inventory.usecases.product.add.AddProductUseCase;
import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class AddCategoryUseCaseTest {
    AddCategoryUseCase addCategoryUseCase;
    @BeforeEach
    public void setUp() {
        addCategoryUseCase= new AddCategoryUseCase();
    }
    @Test
    public void shouldCreateCategoryWithCorrectCategoryData() {
        AddCategoryRequest request = new AddCategoryRequest("1", "Fake", "This is fake.");
        Optional<AddCategoryResponse> response = addCategoryUseCase.execute(request);
        assertTrue(response.isPresent());
        assertEquals("Fake",response.get().categoryEntity().name());
    }

    @Test
    public void shouldNotCreateProductWithEmptyName() {
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("1", "", "This is electronics");
        Throwable throwable = assertThrows(ImsException.class, () -> addCategoryUseCase.execute(addCategoryRequest));
        assertEquals(ExceptionType.CATEGORY_NAME_EMPTY.getMessage(), throwable.getMessage());
    }

    @Test
    public void shouldNotCreateCategoryIfCategoryNameAlreadyExists() {
        AddCategoryRequest requestOne = new AddCategoryRequest("1", "Duplicate", "This is fake.");
        AddCategoryRequest requestTwo = new AddCategoryRequest("2", "Duplicate", "This is not fake.");
        addCategoryUseCase.execute(requestOne);
        Throwable throwable = assertThrows(ImsException.class, () -> addCategoryUseCase.execute(requestTwo));
        assertEquals(ExceptionType.CATEGORY_ALREADY_EXISTS.getMessage(), throwable.getMessage());
    }



}