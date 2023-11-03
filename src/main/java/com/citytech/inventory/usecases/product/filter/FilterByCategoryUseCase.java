package com.citytech.inventory.usecases.product.filter;


import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class FilterByCategoryUseCase implements UseCase<FilterByCategoryRequest, FilterByCategoryResponse> {

    @Override
    public Optional<FilterByCategoryResponse> execute(FilterByCategoryRequest request) {
        List<ProductEntity> filteredProducts = EntityManager.getProductList().stream()
                .filter(product -> product.category().equals(request.name()))
                .collect(Collectors.toList());

        return Optional.of(new FilterByCategoryResponse(filteredProducts));
    }
}
