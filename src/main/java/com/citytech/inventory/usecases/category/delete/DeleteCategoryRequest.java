package com.citytech.inventory.usecases.category.delete;

import com.citytech.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record DeleteCategoryRequest(String name) implements UseCaseRequest {}
