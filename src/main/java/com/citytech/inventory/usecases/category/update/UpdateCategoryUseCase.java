package com.citytech.inventory.usecases.category.update;

import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.CategoryEntity;
import com.citytech.repositories.EntityManager;
import jakarta.inject.Singleton;

import java.util.ListIterator;
import java.util.Optional;

@Singleton
public class UpdateCategoryUseCase implements UseCase<UpdateCategoryRequest, UpdateCategoryResponse> {
    @Override
    public Optional<UpdateCategoryResponse> execute(UpdateCategoryRequest request) {
        this.validateUpdateCategoryRequest(request);

        ListIterator<CategoryEntity> iterator = EntityManager.getCategoriesList().listIterator();

        while (iterator.hasNext()) {
            CategoryEntity category = iterator.next();

            if (request.id().equals(category.id())) {
                CategoryEntity updatedCategory = new CategoryEntity(
                        category.id(),
                        request.name(),
                        request.description()
                );
                iterator.set(updatedCategory);
                return Optional.of(new UpdateCategoryResponse(updatedCategory.name(), updatedCategory.description()));
            }
        }
        return Optional.empty();
    }

    private void validateUpdateCategoryRequest(UpdateCategoryRequest request) {
        if (request.name().isEmpty() || request.description().isEmpty()) {
            throw new ImsException(ExceptionType.EMPTY_FIELDS);
        }

        String categoryId = request.id();
        boolean categoryExists = EntityManager.getCategoriesList().stream()
                .anyMatch(category -> category.id().equals(categoryId));

        if (!categoryExists) {
            throw new ImsException(ExceptionType.CATEGORY_DOES_NOT_EXIST);
        }
    }
}
