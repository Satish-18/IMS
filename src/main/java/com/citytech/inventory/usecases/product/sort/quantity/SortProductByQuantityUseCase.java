package com.citytech.inventory.usecases.product.sort.quantity;

import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

@Singleton
public class SortProductByQuantityUseCase implements UseCase<SortProductByQuantityRequest, SortProductByQuantityResponse> {
    @Override
    public Optional<SortProductByQuantityResponse> execute(SortProductByQuantityRequest request) {
        Collections.sort(EntityManager.getProductList(), Comparator.comparing(ProductEntity::quantity));
        return Optional.of(new SortProductByQuantityResponse(EntityManager.getProductList()));
    }
}
