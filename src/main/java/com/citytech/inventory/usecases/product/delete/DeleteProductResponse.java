package com.citytech.inventory.usecases.product.delete;

import com.citytech.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record DeleteProductResponse(String message) implements UseCaseResponse {}
