package com.citytech.inventory.usecases.category.add;

import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.CategoryEntity;
import com.citytech.repositories.EntityManager;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class AddCategoryUseCase implements UseCase<AddCategoryRequest, AddCategoryResponse> {
    @Override
    public Optional<AddCategoryResponse> execute(AddCategoryRequest request) {
        this.validateAddRequest(request);

        CategoryEntity category = new CategoryEntity(request.id(), request.name(), request.description());
        EntityManager.getCategoriesList().add(category);
        return Optional.of(new AddCategoryResponse(category));
    }

    private void validateAddRequest(AddCategoryRequest request) {
        if (request.name().isEmpty() || request.description().isEmpty()) {
            throw new ImsException(ExceptionType.CATEGORY_NAME_EMPTY);
        }

        String categoryName = request.name();
        boolean categoryExists = EntityManager.getCategoriesList().stream()
                .anyMatch(category -> category.name().equals(categoryName));

        if (categoryExists) {
            throw new ImsException(ExceptionType.CATEGORY_ALREADY_EXISTS);
        }

    }
}
