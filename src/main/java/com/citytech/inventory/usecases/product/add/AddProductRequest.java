package com.citytech.inventory.usecases.product.add;

import com.citytech.platform.usecase.UseCaseRequest;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record AddProductRequest(String id,
                                String name,
                                String category,
                                long quantity) implements UseCaseRequest {
}
