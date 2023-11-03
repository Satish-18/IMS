package com.citytech.inventory.usecases.product.filter;

import com.citytech.platform.usecase.UseCaseResponse;
import com.citytech.repositories.ProductEntity;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Introspected
@Serdeable
public record FilterByCategoryResponse(List<ProductEntity> filteredProducts) implements UseCaseResponse { }

