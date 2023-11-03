package com.citytech.repositories;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private static List<ProductEntity> productList = new ArrayList<>();
    private static List<CategoryEntity> categoriesList= new ArrayList<>();

    public static List<ProductEntity> getProductList() {
        return productList;
    }

    public static void setProductList(List<ProductEntity> productItem) {
        EntityManager.productList = productItem;
    }

    public static List<CategoryEntity> getCategoriesList() {
        return categoriesList;
    }

    public static void setCategoriesList(List<CategoryEntity> categoriesList) {
        EntityManager.categoriesList = categoriesList;
    }
}
