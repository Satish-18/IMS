package com.citytech.inventory.usecases.product.get;


import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import jakarta.inject.Singleton;

import java.util.Optional;


@Singleton
public class GetAllProductsUseCase implements UseCase<GetAllProductsRequest,GetAllProductsResponse> {
    @Override
    public Optional<GetAllProductsResponse> execute(GetAllProductsRequest request) {
        return Optional.of(new GetAllProductsResponse(EntityManager.getProductList()));
    }
}
