package com.citytech.inventory.controllers;

import com.citytech.inventory.controllers.payloads.product.AddProductPayload;
import com.citytech.inventory.controllers.payloads.product.UpdateProductPayload;
import com.citytech.inventory.converters.ProductConverter;
import com.citytech.inventory.usecases.product.add.AddProductRequest;
import com.citytech.inventory.usecases.product.add.AddProductUseCase;
import com.citytech.inventory.usecases.product.delete.DeleteProductRequest;
import com.citytech.inventory.usecases.product.delete.DeleteProductResponse;
import com.citytech.inventory.usecases.product.delete.DeleteProductUseCase;
import com.citytech.inventory.usecases.product.filter.FilterByCategoryRequest;
import com.citytech.inventory.usecases.product.filter.FilterByCategoryUseCase;
import com.citytech.inventory.usecases.product.get.GetAllProductsRequest;
import com.citytech.inventory.usecases.product.get.GetAllProductsUseCase;
import com.citytech.inventory.usecases.product.search.SearchProductsRequest;
import com.citytech.inventory.usecases.product.search.SearchProductsResponse;
import com.citytech.inventory.usecases.product.search.SearchProductsUseCase;
import com.citytech.inventory.usecases.product.sort.alphabetical.SortProductAlphabeticallyRequest;
import com.citytech.inventory.usecases.product.sort.alphabetical.SortProductAlphabeticallyResponse;
import com.citytech.inventory.usecases.product.sort.alphabetical.SortProductAlphabeticallyUseCase;
import com.citytech.inventory.usecases.product.sort.quantity.SortProductByQuantityRequest;
import com.citytech.inventory.usecases.product.sort.quantity.SortProductByQuantityResponse;
import com.citytech.inventory.usecases.product.sort.quantity.SortProductByQuantityUseCase;
import com.citytech.inventory.usecases.product.update.UpdateProductRequest;
import com.citytech.inventory.usecases.product.update.UpdateProductUseCase;
import com.citytech.platform.exception.ExceptionType;
import com.citytech.platform.exception.ImsException;
import com.citytech.platform.rest.RestResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;
import jakarta.inject.Inject;

import java.util.Optional;

@Controller("/product")
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {
    private final AddProductUseCase addProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final SearchProductsUseCase searchProductsUseCase;
    private final SortProductAlphabeticallyUseCase sortProductAlphabeticallyUseCase;
    private final SortProductByQuantityUseCase sortProductByQuantityUseCase;
    private final FilterByCategoryUseCase filterByCategoryUseCase;

    @Inject
    public ProductController(AddProductUseCase addProductUseCase,
                             GetAllProductsUseCase getAllProductsUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase,
                             SearchProductsUseCase searchProductsUseCase,
                             SortProductAlphabeticallyUseCase sortProductAlphabeticallyUseCase,
                             SortProductByQuantityUseCase sortProductByQuantityUseCase,
                             FilterByCategoryUseCase filterByCategoryUseCase) {
        this.addProductUseCase = addProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.searchProductsUseCase = searchProductsUseCase;
        this.sortProductAlphabeticallyUseCase = sortProductAlphabeticallyUseCase;
        this.sortProductByQuantityUseCase = sortProductByQuantityUseCase;
        this.filterByCategoryUseCase = filterByCategoryUseCase;
    }

    @Post("/add")
    public HttpResponse<RestResponse> addProducts(@Body AddProductPayload addProductPayload){
        AddProductRequest addProductRequest= ProductConverter.toRequest(addProductPayload);
        addProductUseCase.execute(addProductRequest);
        return HttpResponse.ok(RestResponse.success("Product Added"));
    }

    @Get("/")
    public ModelAndView<Object> getAllProducts() throws ImsException {
        GetAllProductsRequest request = new GetAllProductsRequest();

        if (this.getAllProductsUseCase.execute(request).isEmpty()) {
            throw new ImsException(ExceptionType.NOT_AVAILABLE);
        }

        return new ModelAndView<>("home", this.getAllProductsUseCase.execute(request).get());
    }

    @Post("/update/{id}")
    public HttpResponse<RestResponse> updateProducts(@Body UpdateProductPayload updateProductPayload, @PathVariable String id){
        UpdateProductRequest updateProductRequest = ProductConverter.toRequest(updateProductPayload,id);
        updateProductUseCase.execute(updateProductRequest);
        return HttpResponse.ok(RestResponse.success("Product Update successfully"));

    }
    @Delete("/delete/{id}")
    public HttpResponse<RestResponse> deleteProduct(@PathVariable String id){
        Optional<DeleteProductResponse> deleteProduct = deleteProductUseCase.execute(new DeleteProductRequest(id));

        if (deleteProduct.isPresent()) {
            return HttpResponse.ok(RestResponse.success("Product Deleted Successfully"));
        }

        return HttpResponse.badRequest(RestResponse.error("400","NO such product is present"));

    }


    @Get("/search")
    public HttpResponse<RestResponse> searchProduct(@QueryValue String name){
        Optional<SearchProductsResponse> searchProductResponse=searchProductsUseCase.execute(new SearchProductsRequest(name));

        if (searchProductResponse.isEmpty()) {
            throw new ImsException(ExceptionType.NOT_AVAILABLE);
        }

        return HttpResponse.ok(RestResponse.success(searchProductResponse.get()));
    }

    @Get("sort-by-alphabetic")
    public HttpResponse<RestResponse> getAllProductsAlpahbetically(){
        Optional<SortProductAlphabeticallyResponse> getAllProductsResponse=sortProductAlphabeticallyUseCase.execute(new SortProductAlphabeticallyRequest());

        if (getAllProductsResponse.isEmpty()) {
            throw new ImsException(ExceptionType.NOT_AVAILABLE);
        }

        SortProductAlphabeticallyResponse products=getAllProductsResponse.get();
        return HttpResponse.ok(RestResponse.success(products));


    }

    @Get("/sort-by-quantity")
    public HttpResponse<RestResponse> getAllProductsByQuantity(){
        Optional<SortProductByQuantityResponse> getAllProductsResponse=sortProductByQuantityUseCase.execute(new SortProductByQuantityRequest());

        if (getAllProductsResponse.isEmpty()) {
            throw new ImsException(ExceptionType.NOT_AVAILABLE);
        }

        SortProductByQuantityResponse products=getAllProductsResponse.get();
        return HttpResponse.ok(RestResponse.success(products));

    }

    @Get("/filter/{categoryName}")
    public HttpResponse<RestResponse> sortProductByCategory(@PathVariable String categoryName) {
        FilterByCategoryRequest request = new FilterByCategoryRequest(categoryName);

        if (this.filterByCategoryUseCase.execute(request).isPresent()) {
            throw new ImsException(ExceptionType.NOT_AVAILABLE);
        }

        return HttpResponse.ok(RestResponse.success(this.filterByCategoryUseCase.execute(request).get()));
    }
}
