package com.citytech.inventory.usecases.product.update;

import com.citytech.platform.usecase.UseCaseResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record UpdateProductResponse(String id,
                                    String name,
                                    String category,
                                    long quantity) implements UseCaseResponse {}
