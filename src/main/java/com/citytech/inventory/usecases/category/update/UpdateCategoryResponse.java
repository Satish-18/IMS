package com.citytech.inventory.usecases.category.update;

import com.citytech.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record UpdateCategoryResponse(String name,
                                     String description) implements UseCaseResponse {}
