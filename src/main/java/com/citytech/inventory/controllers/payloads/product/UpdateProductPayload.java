package com.citytech.inventory.controllers.payloads.product;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record UpdateProductPayload (String name,
                                   String category,
                                   long quantity){}
