package com.citytech.inventory.usecases.product.add;

import com.citytech.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record AddProductResponse() implements UseCaseResponse {
}
