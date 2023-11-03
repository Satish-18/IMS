package com.citytech.inventory.converters;

import com.citytech.inventory.controllers.payloads.product.AddProductPayload;
import com.citytech.inventory.controllers.payloads.product.UpdateProductPayload;
import com.citytech.inventory.usecases.product.add.AddProductRequest;
import com.citytech.inventory.usecases.product.update.UpdateProductRequest;

import java.util.UUID;

public class ProductConverter {

    public static AddProductRequest toRequest(AddProductPayload payload) {
        return new AddProductRequest(
                UUID.randomUUID().toString().substring(0,6),
                payload.name(),
                payload.category(),
                payload.quantity());
    }
    public static UpdateProductRequest toRequest(UpdateProductPayload payload, String id) {
        return new UpdateProductRequest(
                id,
                payload.name(),
                payload.category(),
                payload.quantity());
    }
}
