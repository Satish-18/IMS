package com.citytech.inventory.usecases.product.get;
import com.citytech.platform.usecase.UseCaseResponse;
import com.citytech.repositories.ProductEntity;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Introspected
@Serdeable
public record GetAllProductsResponse(List<ProductEntity> productsList) implements UseCaseResponse {}
