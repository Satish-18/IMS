package com.citytech.inventory.usecases.product.search;
import com.citytech.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record SearchProductsRequest(String name) implements UseCaseRequest {}
