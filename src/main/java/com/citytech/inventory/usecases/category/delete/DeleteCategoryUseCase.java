package com.citytech.inventory.usecases.category.delete;
import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.platform.usecase.UseCase;
import com.citytech.repositories.CategoryEntity;
import com.citytech.repositories.EntityManager;
import com.citytech.repositories.ProductEntity;
import com.github.jknack.handlebars.internal.antlr.atn.PredicateTransition;
import jakarta.inject.Singleton;

import java.util.ListIterator;
import java.util.Optional;

@Singleton
public class DeleteCategoryUseCase implements UseCase<DeleteCategoryRequest, DeleteCategoryResponse> {

    @Override
    public Optional<DeleteCategoryResponse> execute(DeleteCategoryRequest request) {
        this.validateDeleteRequest(request);

        String categoryName = request.name();

        EntityManager.getCategoriesList().removeIf(category -> categoryName.equals(category.name()));

        // deletes all the products associated with the category.
        EntityManager.getProductList().removeIf(product -> product.category().equals(categoryName));

        return Optional.of(new DeleteCategoryResponse(categoryName));
    }

    private void validateDeleteRequest(DeleteCategoryRequest request) {
        String categoryName = request.name();

        Optional<CategoryEntity> categoryToRemove = EntityManager.getCategoriesList().stream()
                .filter(category -> category.name().equals(categoryName))
                .findFirst();

        if (categoryToRemove.isEmpty()) {
            throw new ImsException(ExceptionType.CATEGORY_DOES_NOT_EXIST);
        }
    }


}
