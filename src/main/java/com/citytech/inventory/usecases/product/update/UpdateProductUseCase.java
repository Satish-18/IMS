package com.citytech.inventory.usecases.product.update;


import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import jakarta.inject.Singleton;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;

@Singleton
public class UpdateProductUseCase implements UseCase<UpdateProductRequest, UpdateProductResponse> {

    @Override
    public Optional<UpdateProductResponse> execute(UpdateProductRequest request) {
        ListIterator<ProductEntity> iterator= EntityManager.getProductList().listIterator();

        while(iterator.hasNext()){
            ProductEntity product = iterator.next();

            if(request.id().equals(product.id())){
                ProductEntity updatedProduct = new ProductEntity(product.id(), request.name(), request.category(), request.quantity());
                iterator.set(updatedProduct);

                return Optional.of(new UpdateProductResponse(updatedProduct.id(), updatedProduct.name(),updatedProduct.category(), updatedProduct.quantity()));
            }
        }
        return Optional.empty();
    }
}
