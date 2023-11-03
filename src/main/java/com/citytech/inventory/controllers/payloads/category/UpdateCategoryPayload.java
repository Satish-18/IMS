package com.citytech.inventory.controllers.payloads.category;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record UpdateCategoryPayload(String name,
                                    String description){}
