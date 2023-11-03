package com.citytech.inventory.usecases.category.get;

import com.citytech.platform.usecase.UseCaseResponse;
import com.citytech.repositories.CategoryEntity;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Introspected
@Serdeable
public record GetAllCategoriesResponse(List<CategoryEntity> categoriesList) implements UseCaseResponse {}
