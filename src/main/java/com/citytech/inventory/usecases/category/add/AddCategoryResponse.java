package com.citytech.inventory.usecases.category.add;

import com.citytech.platform.usecase.UseCaseResponse;
import com.citytech.repositories.CategoryEntity;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record AddCategoryResponse(CategoryEntity categoryEntity) implements UseCaseResponse {
}
