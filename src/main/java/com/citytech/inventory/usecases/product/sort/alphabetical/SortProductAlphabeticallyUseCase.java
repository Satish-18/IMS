package com.citytech.inventory.usecases.product.sort.alphabetical;

import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

@Singleton
public class SortProductAlphabeticallyUseCase implements UseCase<SortProductAlphabeticallyRequest,SortProductAlphabeticallyResponse> {
    @Override
    public Optional<SortProductAlphabeticallyResponse> execute(SortProductAlphabeticallyRequest request) {
        Collections.sort(EntityManager.getProductList(), Comparator.comparing(ProductEntity::name));
        return Optional.of(new SortProductAlphabeticallyResponse(EntityManager.getProductList()));
    }
}
