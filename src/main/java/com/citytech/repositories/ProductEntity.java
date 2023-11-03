package com.citytech.repositories;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record ProductEntity(String id,
                            String name,
                            String category,
                            long quantity) {
}
