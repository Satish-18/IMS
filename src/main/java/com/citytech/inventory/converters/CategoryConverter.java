package com.citytech.inventory.converters;
import com.citytech.inventory.controllers.payloads.category.AddCategoryPayload;
import com.citytech.inventory.usecases.category.add.AddCategoryRequest;

import java.util.UUID;

public class CategoryConverter {
    public static AddCategoryRequest toRequest(AddCategoryPayload payload) {
        return new AddCategoryRequest(
                UUID.randomUUID().toString().substring(0,6),
                payload.name(),
                payload.description());
    }
}
