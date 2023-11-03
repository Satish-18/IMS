package com.citytech.platform.exception;

public enum ExceptionType {

    PRODUCT_NAME_NONEMPTY("ERR-001","Product Name cannot be empty"),
    PRODUCT_CATEGORY_NONEMPTY("ERR-001","Catgeory Name cannot be empty"),
    PRODUCT_QUANTITY_NEGATIVE("ERR-002","Product Quantity cannot be negative"),
    CATEGORY_NAME_EMPTY("ERR-003","Category Name cannot be empty"),
    NOT_AVAILABLE("ERR-004", "Input not available."),
    CATEGORY_ALREADY_EXISTS("ERR-005", "Category already exists. You cannot add the same category twice."),
    CATEGORY_DOES_NOT_EXIST("ERR-006", "Category does not exist."),
    EMPTY_FIELDS("ERR-007", "Input field is empty.");

    private String code;

    private String message;

    ExceptionType(String code,
                  String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
