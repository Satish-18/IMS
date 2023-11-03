package com.citytech.inventory.controllers;

import com.citytech.inventory.controllers.payloads.category.AddCategoryPayload;
import com.citytech.inventory.controllers.payloads.category.DeleteCategoryPayload;
import com.citytech.inventory.controllers.payloads.category.UpdateCategoryPayload;
import com.citytech.inventory.converters.CategoryConverter;
import com.citytech.inventory.usecases.category.add.AddCategoryRequest;
import com.citytech.inventory.usecases.category.add.AddCategoryUseCase;
import com.citytech.inventory.usecases.category.delete.DeleteCategoryRequest;
import com.citytech.inventory.usecases.category.delete.DeleteCategoryUseCase;
import com.citytech.inventory.usecases.category.get.GetAllCategoriesRequest;
import com.citytech.inventory.usecases.category.get.GetAllCategoriesUseCase;
import com.citytech.inventory.usecases.category.update.UpdateCategoryRequest;
import com.citytech.inventory.usecases.category.update.UpdateCategoryUseCase;
import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.platform.rest.RestResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;
import jakarta.inject.Inject;

@Controller("/category")
@Consumes(MediaType.APPLICATION_JSON)
class CategoryController {

    private final AddCategoryUseCase addCategoryUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @Inject
    CategoryController(AddCategoryUseCase addCategoryUseCase, GetAllCategoriesUseCase getAllCategoriesUseCase, UpdateCategoryUseCase updateCategoryUseCase, DeleteCategoryUseCase deleteCategoryUseCase) {
        this.addCategoryUseCase = addCategoryUseCase;
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @Get("/")
    public ModelAndView<?> getCategoryView() throws ImsException {
        GetAllCategoriesRequest request = new GetAllCategoriesRequest();

        if (this.getAllCategoriesUseCase.execute(request).isEmpty()) {
            throw new ImsException(ExceptionType.NOT_AVAILABLE);
        }
        return new ModelAndView<>("category", this.getAllCategoriesUseCase.execute(request).get());
    }

    @Get("/list")
    public HttpResponse<RestResponse> getCategories() throws ImsException {
        GetAllCategoriesRequest request = new GetAllCategoriesRequest();

        if (this.getAllCategoriesUseCase.execute(request).isEmpty()) {
            throw new ImsException(ExceptionType.NOT_AVAILABLE);
        }

        return HttpResponse.ok(RestResponse.success(this.getAllCategoriesUseCase.execute(request).get()));
    }

    @Post("/add")
    public HttpResponse<RestResponse<Object>> addCategory(@Body AddCategoryPayload payload) {
        AddCategoryRequest request = CategoryConverter.toRequest(payload);
        this.addCategoryUseCase.execute(request);
        return HttpResponse.ok(RestResponse.success());
    }

    @Post("/update/{id}")
    public HttpResponse<RestResponse<Object>> updateCategory(@Body UpdateCategoryPayload payload, @PathVariable String id) {
        UpdateCategoryRequest request = new UpdateCategoryRequest(id, payload.name(), payload.description());
        this.updateCategoryUseCase.execute(request);
        return HttpResponse.ok(RestResponse.success());
    }

    @Delete("/delete/{name}")
    public HttpResponse<RestResponse<Object>> deleteCategory(@PathVariable String name) {
        DeleteCategoryRequest request = new DeleteCategoryRequest(name);
        this.deleteCategoryUseCase.execute(request);
        return HttpResponse.ok(RestResponse.success());
    }

}
