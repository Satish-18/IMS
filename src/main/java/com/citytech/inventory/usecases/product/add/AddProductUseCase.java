package com.citytech.inventory.usecases.product.add;

import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class AddProductUseCase implements UseCase<AddProductRequest, AddProductResponse> {

    @Override
    public Optional<AddProductResponse> execute(AddProductRequest request) {
        this.validateAddProductRequest(request);

        ProductEntity productEntity = new ProductEntity(request.id(), request.name(), request.category(), request.quantity());
        EntityManager.getProductList().add(productEntity);
        return Optional.of(new AddProductResponse());
    }

    private void validateAddProductRequest(AddProductRequest request) {
        if (request.name().isEmpty()) {
            throw new ImsException(ExceptionType.PRODUCT_NAME_NONEMPTY);
        }
        if (request.category().isEmpty()) {
            throw new ImsException(ExceptionType.CATEGORY_NAME_EMPTY);
        }

        if (request.quantity() < 0) {
            throw new ImsException(ExceptionType.PRODUCT_QUANTITY_NEGATIVE);
        }
    }
}
