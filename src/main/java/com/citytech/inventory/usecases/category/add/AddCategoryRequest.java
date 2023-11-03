package com.citytech.inventory.usecases.category.add;

import com.citytech.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record AddCategoryRequest(String id,
                                 String name,
                                 String description) implements UseCaseRequest {
}
