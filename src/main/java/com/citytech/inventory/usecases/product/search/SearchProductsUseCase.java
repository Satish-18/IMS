package com.citytech.inventory.usecases.product.search;


import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class SearchProductsUseCase implements UseCase<SearchProductsRequest, SearchProductsResponse> {

    @Override
    public Optional<SearchProductsResponse> execute(SearchProductsRequest request) {
        List<ProductEntity> matchingProducts = new ArrayList<>();

        if(request.name() == null){
            return Optional.of(new SearchProductsResponse(matchingProducts));
        }

        for (ProductEntity product : EntityManager.getProductList()) {
            if (product.name().toLowerCase().contains(request.name().toLowerCase())) {
                matchingProducts.add(product);
            }
        }

        return Optional.of(new SearchProductsResponse(matchingProducts));
    }
}
