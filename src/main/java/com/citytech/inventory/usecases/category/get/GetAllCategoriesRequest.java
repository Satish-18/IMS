package com.citytech.inventory.usecases.category.get;

import com.citytech.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record GetAllCategoriesRequest() implements UseCaseRequest {}
