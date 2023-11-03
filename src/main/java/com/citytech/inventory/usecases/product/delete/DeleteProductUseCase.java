package com.citytech.inventory.usecases.product.delete;

import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import jakarta.inject.Singleton;

import java.util.Iterator;
import java.util.Optional;

@Singleton
public class DeleteProductUseCase implements UseCase<DeleteProductRequest, DeleteProductResponse> {
    @Override
    public Optional<DeleteProductResponse> execute(DeleteProductRequest request) {
        Iterator<ProductEntity> iterator = EntityManager.getProductList().iterator();

        while (iterator.hasNext()) {
            ProductEntity item = iterator.next();

            if (item.id().equals(request.id())) {
                iterator.remove();
                return Optional.of(new DeleteProductResponse("Product " + item.id() + " Deleted Successfully"));
            }

        }
        return Optional.empty();
    }

}
