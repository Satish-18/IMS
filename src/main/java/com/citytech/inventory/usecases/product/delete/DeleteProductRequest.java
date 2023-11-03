package com.citytech.inventory.usecases.product.delete;

import com.citytech.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record DeleteProductRequest(String id) implements UseCaseRequest {}
