package com.citytech.inventory.usecases.product.filter;

import com.citytech.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record FilterByCategoryRequest(String name) implements UseCaseRequest {
}
