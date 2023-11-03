package com.citytech.inventory.usecases.category.get;



import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.EntityManager;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class GetAllCategoriesUseCase implements UseCase<GetAllCategoriesRequest, GetAllCategoriesResponse> {
    @Override
    public Optional<GetAllCategoriesResponse> execute(GetAllCategoriesRequest request) {
        return Optional.of(new GetAllCategoriesResponse(EntityManager.getCategoriesList()));
    }
}
