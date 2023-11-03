package com.citytech.inventory.usecases.category.delete;
import com.citytech.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record DeleteCategoryResponse(String name) implements UseCaseResponse {}
