package com.citytech.platform.usecase;

import com.citytech.repositories.CategoryEntity;
import com.citytech.repositories.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@FunctionalInterface
public interface UseCase<I extends UseCaseRequest, O extends UseCaseResponse> {
    Optional<O> execute(I request);
}
