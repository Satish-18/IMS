package com.citytech.inventory.usecases.product.search;

import com.citytech.platform.usecase.UseCaseResponse;
import com.citytech.repositories.ProductEntity;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Introspected
@Serdeable
public record SearchProductsResponse(List<ProductEntity> productEntity) implements UseCaseResponse {}
